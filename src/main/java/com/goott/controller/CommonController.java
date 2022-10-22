package com.goott.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goott.domain.MemberVO;
import com.goott.service.MailSendService;
import com.goott.service.MemberService;
import com.goott.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
    @Inject
    private MailSendService mailSendService;
    @Inject
    MemberService memberService;
    @Inject
    UserService userService;


    @RequestMapping(value = "alert", method = RequestMethod.GET)
    public String alert() {
        return "/common/alert";
    }

    @RequestMapping(value = "agree", method = RequestMethod.GET)
    public String agreeGet() {
        return "/common/agree";
    }

    @RequestMapping(value = "join", method = RequestMethod.GET)
    public String joinGet(@RequestParam String member_email_agree, Model model) {
        model.addAttribute("member_email_agree", member_email_agree);
        return "/common/join";
    }

    @RequestMapping(value = "join", method = RequestMethod.POST)
    public String joinPost(MemberVO memberVO, Model model) {

        //기본 프로필 이미지
        memberVO.setMember_profile_img_url("/resources/img/user/basic_profile.jpg");

        model.addAttribute("member", memberVO);
        //가입 신청 유저 이메일 주소로 메일 발송 , 생성된 랜덤 코드 리턴
        String code = mailSendService.joinEmail(memberVO.getMember_email());

        model.addAttribute("code", code);
        return "common/auth_mail";
    }

    /**
     * 이메일 인증 체크 후 회원가입 로직 실행할 메서드
     * @param memberVO 회원 엔티티
     * @return 메인화면
     */
    @RequestMapping(value = "joinSuccess", method = RequestMethod.POST)
    public String joinSuccessPost(MemberVO memberVO) {

        memberService.joinMember(memberVO);

        return "common/home";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginGet() {
        return "common/login";
    }

    /**
     * @param member_id 로그인 아이디
     * @param member_pw 로그인 비밀번호
     * @return 알림창
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String member_id, @RequestParam String member_pw, Model model, HttpServletRequest request) {


        String msg = memberService.loginCheck(member_id, member_pw);


        //로그인 성공 이라면 세션에 로그인 아이디 추가
        if (msg.equals("로그인 성공.")) {
            HttpSession session = request.getSession();
            session.setAttribute("login_id", member_id);
            String login_auth = memberService.getUserAuth(member_id);
            //권한 저장
            session.setAttribute("login_auth", login_auth);

            //비밀번호 초기화 상태인제 체크
            String resultText = memberService.getInitPw(member_id);
            if (resultText.equals("y")) {
                model.addAttribute("msg", member_id + "님 비밀번호를 재설정 해주세요.");
                model.addAttribute("url", "user/change_password");

                return "common/alert";
            }

            //기존에 접속시도했던 주소가 있다면
            if (session.getAttribute("prior_uri") != null) {
                String prior_uri = session.getAttribute("prior_uri").toString();
                String query_string = session.getAttribute("query_string").toString();
//				log.info("세션에 저장된 접속하려던 주소 : " + prior_uri);
                //세션에 이전 접속 주소 초기화
                session.removeAttribute("prior_uri");
                session.removeAttribute("query_string");
                //이전 접속 주소로 이동

                model.addAttribute("msg", member_id + "님 환영합니다.");
                model.addAttribute("url", prior_uri + "?" + query_string);

                return "common/alert";


            }
            //기존에 접속 시도 했던 주소가 없다면
            else {
                model.addAttribute("msg", member_id + "님 환영합니다.");
                model.addAttribute("url", "/");
                return "common/alert";
            }
        }
        //로그인 시도중 실패했다면 해당 텍스트 리턴
        else {
            model.addAttribute("msg", msg);
            model.addAttribute("url", "/login");
            return "common/alert";
        }
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        //로그아웃 처리시 모든 세션 지우기
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 아이디 중복 확인
     * @param param 로그인 아이디
     * @return 검색 아이디 수
     */
    @ResponseBody
    @RequestMapping(value = "checkDupIdAjax", method = RequestMethod.POST)
    public String checkDupIdAjax(@RequestBody Map<String, String> param) {

        String member_id = param.get("id");
        String result = Integer.toString(memberService.checkDupId(member_id));
        return result;

    }

    /**
     * @param param 검색할 이메일 담은 제이슨
     * @return 검색 이메일 수
     */
    @ResponseBody
    @RequestMapping(value = "checkDupEmailAjax", method = RequestMethod.POST)
    public String checkDupEmailAjax(@RequestBody Map<String, String> param) {

        String member_email = param.get("email");
        String result = Integer.toString(memberService.checkDupEmail(member_email));
        return result;

    }

    @RequestMapping(value = "forgot", method = RequestMethod.GET)
    public String forgotGet() {

        return "common/forgot";
    }

    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public String forgotPost(@RequestParam String member_id, @RequestParam String member_email, Model model) {


        String resultText = userService.forgotPassword(member_id, member_email);
        //비밀번호 초기화 이메일을 발송 하였다면
        if (resultText.equals("입력하신 메일로 초기화 비밀번호를 발송하였습니다. 로그인후 비밀번호를 재설정 해주세요.")) {
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/login");
            return "common/alert";
        }
        //아이디나 이메일정보가 올바르지 않다면
        else {
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/forgot");
            return "common/alert";
        }

    }

    /**
     * 헤더 구역 로그인 유저 프로필 이미지 불러오기
     * @param param 로그인 유저 아이디
     * @return 로그인 유저 프로필 이미지 주소
     */
    @ResponseBody
    @RequestMapping(value = "/getUserImg", method = RequestMethod.POST)
    public Map<String, Object> getUserImg(@RequestBody Map<String, Object> param){
        //회원 아이디
        String member_id = param.get("member_id").toString();

        //회원 프로필 이미지 주소
        String member_profile_img_url = userService.getUserImgUrl(member_id);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("member_profile_img_url", member_profile_img_url);

        return returnMap;
    }


}
