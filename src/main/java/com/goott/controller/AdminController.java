package com.goott.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goott.domain.MemberVO;
import com.goott.service.AdminService;
import com.goott.service.SessionService;

import lombok.extern.log4j.Log4j;

@RequestMapping(value = {"/admin/*", "/admin"})
@Log4j
@Controller
public class AdminController {

    @Inject
    AdminService adminService;
    @Inject
    SessionService sessionService;

    /**
     * 관리자 메인
     *
     * @return 관리자 메인 페이지
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminGet() {
        return "/admin/admin";
    }

    /**
     * 전체 회원 목록 페이지
     *
     * @param model 전체 회원 목록
     * @return 전체 회원 목록 페이지
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<MemberVO> list = adminService.getMemberAllList();
        model.addAttribute("list", list);

        return "/admin/list";
    }

    /**
     * 회원 상세 정보 보기
     *
     * @param member_id 조회할 회원 아이디
     * @param model     회원가입정보, 자유게시판 작성글, 캠핑모임 게시판 작성글, 구매 상품 목록
     * @return 회원 상세 정보 페이지
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam String member_id, Model model) {
        MemberVO member = adminService.getMemberInfo(member_id); // 회원 가입 정보
        List<Map<String, Object>> freeMapList = adminService.getFreeList(member_id); // 자유게시판 작성 글 목록
        List<Map<String, Object>> campingMapList = adminService.getCampingList(member_id); // 캠핑모임 게시판 작성 글 목록
        List<Map<String, Object>> qnaMapList = adminService.getQnaList(member_id); // 질문게시판 작성 글 목록
        List<Map<String, Object>> orderMapList = adminService.getOrderList(member_id); // 상품 구매 이력 목록

        model.addAttribute("member", member);
        model.addAttribute("freeMapList", freeMapList);
        model.addAttribute("campingMapList", campingMapList);
        model.addAttribute("qnaMapList", qnaMapList);
        model.addAttribute("orderMapList", orderMapList);

        return "/admin/detail";
    }

    /**
     * 이메일 수신 동의 회원에게 메일 보내기
     *
     * @return 메일 보내기 페이지
     */
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String sendGet() {
        return "/admin/send";
    }

    /**
     * 이메일 수신 동의 회원에게 메일 보내기
     *
     * @param subject 수신 이메일 제목
     * @param content 수신 이메일 내용
     * @return
     */
    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@RequestParam String subject, @RequestParam String content) {
        adminService.setPromotionEmail(subject, content); // 이메일 보내기
        return "/admin/admin";
    }

    /**
     * 홈페이지 모니터링
     *
     * @param model 각 일간(최근 7일), 주간(최근 4주) 방문자 수, 자유게시판 게시글 작성수, 캠핑 모임 게시판 게시글 작성 수, 매출 정보
     * @return 홈페이지 모니터링 페이지
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public String count(Model model) {
        Map<String, Object> countMap = sessionService.countBasic(); // 일, 주, 총 방문자 수
        List<Map<String, Object>> countTotalDayMap = sessionService.countTotalDay(); // 일간(최근 7일) 방문자 수
        List<Map<String, Object>> countTotalWeekMap = sessionService.countTotalWeek(); // 주간(최근 4주) 방문자 수
        List<Map<String, Object>> countTotalDayMapBoard = sessionService.countTotalDayBoard(); // 일간(최근 7일) 자유게시판 작성 게시글 수
        List<Map<String, Object>> countTotalWeekMapBoard = sessionService.countTotalWeekBoard(); // 주간(최근 4주) 자유게시판 작성 게시글 수
        List<Map<String, Object>> countTotalDayMapQna = sessionService.countTotalDayQna(); // 일간(최근 7일) 질문게시판 작성 게시글 수
        List<Map<String, Object>> countTotalWeekMapQna = sessionService.countTotalWeekQna(); // 주간(최근 4주) 질문게시판 작성 게시글 수
        List<Map<String, Object>> countTotalDayMapShop = sessionService.countTotalDayShop(); // 일간(최근 7일) 매출 금액
        List<Map<String, Object>> countTotalWeekMapShop = sessionService.countTotalWeekShop(); // 주간(최근 4주) 매출 금액

        model.addAttribute("countMap", countMap);
        model.addAttribute("countTotalDayMap", countTotalDayMap);
        model.addAttribute("countTotalWeekMap", countTotalWeekMap);
        model.addAttribute("countTotalDayMapBoard", countTotalDayMapBoard);
        model.addAttribute("countTotalWeekMapBoard", countTotalWeekMapBoard);
        model.addAttribute("countTotalDayMapQna", countTotalDayMapQna);
        model.addAttribute("countTotalWeekMapQna", countTotalWeekMapQna);
        model.addAttribute("countTotalDayMapShop", countTotalDayMapShop);
        model.addAttribute("countTotalWeekMapShop", countTotalWeekMapShop);

        return "/admin/count";
    }
}
