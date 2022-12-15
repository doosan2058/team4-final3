package com.goott.controller;

import com.goott.domain.*;
import com.goott.service.AdminService;
import com.goott.service.MemberService;
import com.goott.service.OrderService;
import com.goott.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Controller
@RequestMapping(value = {"/user", "/user/*"})
public class UserController {

    @Inject
    OrderService orderService;
    @Inject
    UserService userService;
    @Inject
    MemberService memberService;
    @Inject
    AdminService adminService;

    /**
     * 유저 정보 페이지 요청
     *
     * @param request
     * @param model   주문 리스트, 구매 리스트, 회원 엔티티, 자유게시판 작성글 목록, 캠핑게시판 작성글 목록, 질문게시판 작성글 목록
     * @return 유저 정보 페이지
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userGet(HttpServletRequest request, Model model) {


        // 로그인 유저 아이디
        // 인터셉터로 로그인 여부 확인 하기때문에 따로 로그인 아이디 null 처리 불필요
        HttpSession session = request.getSession();
        String member_id = session.getAttribute("login_id").toString();
        // 주문 정보 리스트 가져오기
        List<OrderVO> orderList = orderService.getOrderList(member_id);
        // 구매 확정 리스트
        List<SalesVO> salesList = userService.getUserSalesList(member_id);
        // 회원 정보
        MemberVO memberVO = memberService.getMemberInfo(member_id);
        List<Map<String, Object>> freeMapList = adminService.getFreeList(member_id); // 자유게시판 작성글 목록
        List<Map<String, Object>> campingMapList = adminService.getCampingList(member_id); // 캠핑 모임 게시판 작성글 목록
        List<Map<String, Object>> qnaMapList = adminService.getQnaList(member_id); // 질문게시판 작성글 목록

        model.addAttribute("orderList", orderList);
        model.addAttribute("salesList", salesList);
        model.addAttribute("memberVO", memberVO);
        model.addAttribute("freeMapList", freeMapList);
        model.addAttribute("campingMapList", campingMapList);
        model.addAttribute("qnaMapList", qnaMapList);

        return "/user/mypage";
    }

    /**
     * 프로필 이미지 변경
     *
     * @param file      프로필 이미지
     * @param member_id 유저 아이디
     * @return 프로필 이미지 변경 처리 결과
     */
    @ResponseBody
    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public Map<String, Object> profile(@RequestParam MultipartFile file, @RequestParam String member_id) {
        return userService.changeProfileImg(file, member_id);
    }

    /**
     * 리뷰글 작성 페이지 요청
     *
     * @param sales_id 매출 번호
     * @param model    매출 엔티티
     * @return 리뷰글 작성 페이지
     */
    @RequestMapping(value = "review", method = RequestMethod.GET)
    public String reviewGet(@RequestParam int sales_id, Model model) {
        model.addAttribute("sales_id", sales_id); // 매출 엔티티
        return "/user/review";
    }

    /**
     * 리뷰글 등록
     *
     * @param fileImg         첨부파일(이미지)
     * @param fileVideo       첨부파일(동영상)
     * @param productReviewVO 리뷰 엔티티
     * @param sales_id        매출 번호
     * @param model           리뷰 등록 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "review", method = RequestMethod.POST)
    public String reviewGet(@RequestParam MultipartFile fileImg, @RequestParam MultipartFile fileVideo, ProductReviewVO productReviewVO, @RequestParam int sales_id, Model model) {
        //이미지 업로드  주소 초기화
        productReviewVO.setProduct_review_img_url("no url");
        //비디오 업로드 주소 초기화
        productReviewVO.setProduct_review_video_url("no url");
        //매출 정보 가져오기
        Map<String, Object> map = userService.getReviewInfo(sales_id);
        //구매자 정보 가져오기
        String member_id = map.get("member_id").toString();
        //상품 정보
        int product_id = Integer.parseInt(map.get("product_id").toString());
        //리뷰 엔티티에 리뷰 정보 넣기
        productReviewVO.setMember_id(member_id);
        productReviewVO.setProduct_id(product_id);
        //리뷰 작성
        String resultText = userService.writeReview(productReviewVO, sales_id, fileImg, fileVideo);

        model.addAttribute("msg", resultText);
        model.addAttribute("url", "user");

        return "/common/alert";
    }

    /**
     * 유저 탈퇴 처리
     *
     * @param member_id 회원 아이디
     * @param request
     * @param model     삭제 처리 결과
     * @return 알람창
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam String member_id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        //세션에 로그인 된 상태인제 체크
        if (session.getAttribute("login_id") != null) {
            String login_id = session.getAttribute("login_id").toString();
            //세션에 로그인 된 아이디와 삭제 요청한 아이디가 일치하는지 한번 더 확인
            if (member_id.equals(login_id)) {
                //로직 작성
                int result = memberService.doWithDrawal(member_id);
                if (result == 1) { // 삭제 처리 업데이트 성공
                    model.addAttribute("msg", "탈퇴 완료 되었습니다.");
                    model.addAttribute("url", "/");
                    //세션 삭제
                    session.invalidate();
                    return "/common/alert";
                } else if (result == 0) { // 업데이트 된 레코드 수가 0이면 없는 아이디
                    model.addAttribute("msg", "존재하지 않는 아이디 입니다. 확인후 다시 시도해 주세요.");
                    model.addAttribute("url", "/");
                    return "/common/alert";
                } else { // 삭제 처리 에러
                    model.addAttribute("msg", "죄송합니다. 잠시후 다시 시도해 주세요.");
                    model.addAttribute("url", "/");
                    return "/common/alert";
                }

            }
            // 세션에 저장된 아이디와 삭제 요청한 아이디가 일치하지 않으면 잘못된 요청
            else {
                model.addAttribute("msg", "잘못된 방식으로 삭제 요청을 하였습니다. 확인 후 다시 시도해 주세요.");
                //세션 삭제
                session.invalidate();
                model.addAttribute("url", "/");
                return "/common/alert";
            }
        }
        // 세션 로그인 상태가 아니라면 잘못된 요청
        else {
            model.addAttribute("msg", "로그인 되지 않은 유저입니다. 로그인 후 탈퇴 신청 해주세요.");
            model.addAttribute("url", "login");
            return "/common/alert";
        }

    }

    /**
     * 유저 비밀번호 체크
     *
     * @param param 회원 아이디, 회원 비밀번호
     * @return 비밀번호 체크 결과 text
     */
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkPw(@RequestBody Map<String, Object> param) {

        String member_id = param.get("member_id").toString(); // 회원 아이디
        String member_pw = param.get("member_pw").toString(); // 회원 비밀번호

        boolean result = memberService.checkPw(member_id, member_pw); // 비밀번호 체크
        if (result) {
            return "일치";
        } else {
            return "비밀번호가 일치하지 않습니다.";
        }
    }

    /**
     * 비밀번호 변경 페이지 요청
     *
     * @return 비밀번호 변경 페이지
     */
    @RequestMapping(value = "change_password", method = RequestMethod.GET)
    public String changePw() {
        return "user/change_password";
    }

    /**
     * 비밀번호 변경
     *
     * @param member_pw 비밀번호
     * @param request
     * @param model     비밀번호 변경 처리 결과
     * @return 알람창
     */
    @RequestMapping(value = "change_password", method = RequestMethod.POST)
    public String changePwPost(@RequestParam String member_pw, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        //세션에 로그인 되어 있다면
        if (session.getAttribute("login_id") != null) {
            String member_id = session.getAttribute("login_id").toString();
            String resultText = userService.changeUserPw(member_id, member_pw); // 비밀번호 변경
            //비밀번호 변경 성공
            if (resultText.equals("비밀번호가 변경 되었습니다.")) {
                model.addAttribute("url", "/logout");
                model.addAttribute("msg", resultText + " 다시 로그인 해 주세요.");
            }
            //비밀번호 변경 실패
            else {
                model.addAttribute("url", "/user");
                model.addAttribute("msg", resultText);
            }
            return "/common/alert";
        }
        //세션에 로그인되 있지 않다면
        else {
            model.addAttribute("url", "로그인이 필요한 서비스 입니다.");
            model.addAttribute("msg", "/login");
            return "/common/alert";
        }

    }

    /**
     * 회원 장바구니 페이지 요청
     *
     * @param request
     * @param model   장바구니에 등록된 상품 목록
     * @return
     */
    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basketGet(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        // 관리자 접근
        if (session.getAttribute("login_auth").toString().equals("관리자")) {
            model.addAttribute("msg", "잘못된 접근입니다. 확인후 다시 시도해 주세요.");
            model.addAttribute("url", "/");
            return "/common/alert";
        }
        // 회원 접근
        String member_id = session.getAttribute("login_id").toString();
        List<BasketVO> list = userService.selectBasketList(member_id); // 장바구니에 등록된 상품 목록
        model.addAttribute("list", list);

        return "/user/basket";
    }

    /**
     * 리뷰 추천
     *
     * @param param   리뷰글 번호
     * @param request
     * @return 리뷰 추천 결과, 총 리뷰 추천 수
     */
    @ResponseBody
    @RequestMapping(value = "/helpful", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map<String, Object> helpful(@RequestBody Map<String, Object> param, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String member_id = session.getAttribute("login_id").toString();
        int product_review_id = Integer.parseInt(param.get("product_review_id").toString());

        // 리뷰 추천
        String resultText = userService.reviewHelpful(member_id, product_review_id);
        // 리뷰 추천 수
        int count = userService.getHelpful(product_review_id);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("msg", resultText);
        returnMap.put("count", count);
        return returnMap;
    }

    /**
     * 장바구니 상품 삭제
     *
     * @param param 장바구니 번호
     * @return 장바구니 삭제 처리 결과 text
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBasket", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String deleteBasket(@RequestBody Map<String, Object> param) {
        int basket_id = Integer.parseInt(param.get("basket_id").toString());
        userService.deleteBasket(basket_id); // 장바구니 엔티티 삭제

        return "삭제되었습니다.";
    }

    /**
     * 장바구니 상품 구매
     *
     * @param param 장바구니 번호
     * @return 구매할 상품 엔티티
     */
    @ResponseBody
    @RequestMapping(value = "/buyBasket", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map<String, Object> buyBasket(@RequestBody Map<String, Object> param) {
        int basket_id = Integer.parseInt(param.get("basket_id").toString());
        Map<String, Object> returnMap = userService.buyToBasket(basket_id); // 장바구니 상품 구매 처리

        return returnMap;
    }

}
