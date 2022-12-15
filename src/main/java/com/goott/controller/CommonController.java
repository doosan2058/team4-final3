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

    /**
     * 알림창 출력 (msg) 후 목적 페이지 (url) 로 이동
     * 내용은 model 에 담는다
     *
     * @return 알림창 출력 페이지
     */
    @RequestMapping(value = "/alert", method = RequestMethod.GET)
    public String alert() {
        return "/common/alert";
    }

    /**
     * 이용 약관 동의 페이지 이동
     *
     * @return 이용 약관 동의 페이지
     */
    @RequestMapping(value = "/agree", method = RequestMethod.GET)
    public String agreeGet() {
        return "/common/agree";
    }

    /**
     * 회원 가입 페이지 이동
     *
     * @param member_email_agree 이메일 수신 동의 여부
     * @param model
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinGet(@RequestParam String member_email_agree, Model model) {
        model.addAttribute("member_email_agree", member_email_agree);
        return "/common/join";
    }

    /**
     * 회원 가입 하기
     *
     * @param memberVO 회원 가입 정보
     * @param model
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinPost(MemberVO memberVO, Model model) {

        // 기본 프로필 이미지 초기화
        memberVO.setMember_profile_img_url("https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/basic_profile.jpg");
        String code = mailSendService.joinEmail(memberVO.getMember_email()); // 가입 신청한 이메일 주소로 랜덤 코드 발송
        model.addAttribute("member", memberVO);
        model.addAttribute("code", code);
        return "/common/auth_mail";
    }

    /**
     * 이메일 인증 완료된 회원 정보 DB에 저장
     *
     * @param memberVO 회원 엔티티
     * @return 메인화면
     */
    @RequestMapping(value = "/joinSuccess", method = RequestMethod.POST)
    public String joinSuccessPost(MemberVO memberVO) {

        memberService.joinMember(memberVO);

        return "/common/home";
    }

    /**
     * 로그인 페이지 요청
     *
     * @return 로그인 페이지
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "/common/login";
    }

    /**
     * 로그인 처리
     *
     * @param member_id 로그인 아이디
     * @param member_pw 로그인 비밀번호
     * @return 알림창
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String member_id, @RequestParam String member_pw, Model model, HttpServletRequest request) {

        String msg = memberService.loginCheck(member_id, member_pw); // 로그인 체크

        // 로그인 성공, 세션에 로그인 아이디, 권한 저장
        if (msg.equals("로그인 성공.")) {
            HttpSession session = request.getSession();
            session.setAttribute("login_id", member_id); // 아이디
            String login_auth = memberService.getUserAuth(member_id); // 권한
            session.setAttribute("login_auth", login_auth);

            // 비밀번호 초기화 상태 체크
            String resultText = memberService.getInitPw(member_id);
            if (resultText.equals("y")) {
                model.addAttribute("msg", member_id + "님 비밀번호를 재설정 해주세요.");
                model.addAttribute("url", "/user/change_password"); // 비멀번호 변경 페이지로 이동

                return "/common/alert";
            }

            model.addAttribute("msg", member_id + "님 환영합니다.");
            model.addAttribute("url", "/");
            return "/common/alert";

        }
        //로그인 실패
        else {
            model.addAttribute("msg", msg);
            model.addAttribute("url", "/login");
            return "/common/alert";
        }
    }

    /**
     * 로그아웃 요청
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        // 세션에 모든 정보 지우기
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    /**
     * 가입 아이디 중복 확인
     *
     * @param param 가입 아이디
     * @return 중복 아이디 수
     */
    @ResponseBody
    @RequestMapping(value = "/checkDupIdAjax", method = RequestMethod.POST)
    public String checkDupIdAjax(@RequestBody Map<String, String> param) {

        String member_id = param.get("id");
        String result = Integer.toString(memberService.checkDupId(member_id));
        return result;
    }

    /**
     * 가입 이메일 중복 확인
     *
     * @param param 가입 요처한 이메일
     * @return 중복 이메일 수
     */
    @ResponseBody
    @RequestMapping(value = "/checkDupEmailAjax", method = RequestMethod.POST)
    public String checkDupEmailAjax(@RequestBody Map<String, String> param) {

        String member_email = param.get("email");
        String result = Integer.toString(memberService.checkDupEmail(member_email));
        return result;
    }

    /**
     * 비밀번호 초기화 페이지 요청
     *
     * @return 비밀번호 초기화 페이지
     */
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgotGet() {

        return "/common/forgot";
    }

    /**
     * 비밀번호 초기화
     *
     * @param member_id    요청 회원 아이디
     * @param member_email 요청 회원 이메일
     * @param model
     * @return 결과 알림
     */
    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public String forgotPost(@RequestParam String member_id, @RequestParam String member_email, Model model) {

        String resultText = userService.forgotPassword(member_id, member_email);
        // 비밀번호 초기화 이메일 발송
        if (resultText.equals("입력하신 메일로 초기화 비밀번호를 발송하였습니다. 로그인후 비밀번호를 재설정 해주세요.")) {
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/login");
            return "/common/alert";
        }
        // 아이디나 이메일정보가 올바르지 않다면
        else {
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/forgot");
            return "/common/alert";
        }

    }

    /**
     * 쇼핑몰 헤더 로그인 유저 프로필 이미지 주소 정보 불러오기
     *
     * @param param 로그인 유저 아이디
     * @return 로그인 유저 프로필 이미지 주소
     */
    @ResponseBody
    @RequestMapping(value = "/getUserImg", method = RequestMethod.POST)
    public Map<String, Object> getUserImg(@RequestBody Map<String, Object> param) {

        String member_id = param.get("member_id").toString();
        String member_profile_img_url = userService.getUserImgUrl(member_id); // 회원 프로필 이미지 주소
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("member_profile_img_url", member_profile_img_url);

        return returnMap;
    }


}
