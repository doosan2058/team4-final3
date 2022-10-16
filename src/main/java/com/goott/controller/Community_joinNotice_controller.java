package com.goott.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goott.domain.T_camping_VO;
import com.goott.service.Community_joinNotice_service;

@Controller
@RequestMapping("/community/joinNotice/*")
public class Community_joinNotice_controller {

    @Autowired
    private Community_joinNotice_service jservice;

    @GetMapping("/main") // 캠핑모임게시판 메인
    public void getList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Map<String, Object> map, Model model, HttpServletRequest request) {
        String sort_value = (String) map.get("sort_value"); //정렬 조건

        HttpSession session = request.getSession();
        String member_auth = " ";
        //세션에 저장된 아이디
        if (session.getAttribute("login_id") != null) {
            String member_id = session.getAttribute("login_id").toString();
            member_auth = jservice.adminConfirmation(member_id); // 회원 관리자 여부
        }
        model.addAttribute("list", jservice.getList(map)); // 게시글 리스트
        model.addAttribute("count", jservice.listCount(map)); // 게시글 갯수
        model.addAttribute("option", map); // 페이징 처리에 사용할 검색 옵션
        model.addAttribute("member_auth", member_auth); // 관리자 여부 확인
        model.addAttribute("sort_value", sort_value);// 조회순 정렬 종류
    }

    @PostMapping("/go_detail")// 상세페이지 조건
    public String goDetail(@RequestParam Map<String, Object> map) {
        String user_id = map.get("user_id").toString(); //글쓴이
        String login_id = map.get("login_id").toString(); // 현재 로그인중인 아이디
        String camping_id = map.get("camping_id").toString();// 게시글 번호
        if (user_id.equals(login_id)) {
            return "redirect:/community/joinNotice/detail_user?camping_id=" + camping_id; //글쓴이와 로그인 아이디가 일치하면 detail user 페이지로
        } else {
            return "redirect:/community/joinNotice/detail?camping_id=" + camping_id;// 아니면 일반 페이지로
        }
    }

    @GetMapping("/detail") // 게시판 상세 페이지
    public void detailPage(int camping_id, Model model) {
        jservice.increase(camping_id); // 조회수 증가
        String member_id = jservice.getDetail(camping_id).getMember_id();
        model.addAttribute("data", jservice.getDetail(camping_id)); // 상세페이지 정보
        model.addAttribute("reply_data", jservice.replyList(camping_id)); //댓글 정보
        model.addAttribute("reply_count", jservice.replyCount(camping_id)); //댓글 갯수
        model.addAttribute("profile", jservice.getImg(member_id));// 글쓴이 프로필 이미지
    }

    @GetMapping("/detail_user") // 게시판 유저 페이지
    public void detailUserPage(int camping_id, Model model) {
        jservice.increase(camping_id); // 조회수 증가
        String member_id = jservice.getDetail(camping_id).getMember_id();
        model.addAttribute("data", jservice.getDetail(camping_id)); // 상세페이지 정보
        model.addAttribute("reply_data", jservice.replyList(camping_id)); //댓글 정보
        model.addAttribute("reply_count", jservice.replyCount(camping_id)); //댓글 갯수
        model.addAttribute("profile", jservice.getImg(member_id));// 글쓴이 프로필 이미지
    }

    @GetMapping("/input") // 글 입력 페이지
    public void input(Model model) {
    }

    @PostMapping("/input") // 글 입력 페이지에서 글 등록하기
    public String inputPost(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Map<String, Object> map) {
        jservice.input(map); // 글 등록
        jservice.inputPoint(map); // 글 등록하면 포인트 증가
        return "redirect:/community/joinNotice/main";
    }

    @GetMapping("/correction") // 게시글 수정 페이지
    public void correctionPage(int camping_id, Model model) {
        model.addAttribute("data", jservice.getDetail(camping_id));
    }

    @PostMapping("/correction") // 게시글 수정하기
    public String correction(T_camping_VO camping) {
        jservice.correct(camping);
        return "redirect:/community/joinNotice/detail_user?camping_id=" + camping.getCamping_id();
    }

    @PostMapping("/delete") // 게시글 삭제하기
    public String delete(int camping_id) {
        jservice.delete(camping_id);
        return "redirect:/community/joinNotice/main";
    }

    @PostMapping("reply_input") // 댓글 달기
    public ModelAndView reply_input(@RequestBody Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();
        this.jservice.reply_input(map);
        String reply_id = map.get("camping_reply_id").toString(); // 댓글 쓴 아이디
        String id_cast = map.get("camping_id").toString();
        int camping_id = Integer.parseInt(id_cast); // 댓글 달린 게시글 번호
        mv.addObject("data", jservice.getReply(reply_id)); // 방금 달린 댓글 정보
        mv.addObject("reply_count", jservice.replyCount(camping_id)); // 댓글 개수
        mv.setViewName("community/joinNotice/reply");
        return mv;
    }

    @PostMapping("delete_reply") // 댓글 삭제
    @ResponseBody
    public void delete_reply(@RequestBody Map<String, Object> map) {
        jservice.delete_reply(map);
    }

    @PostMapping("view_more") // 댓글 view more
    public ModelAndView view_more(@RequestBody Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", jservice.viewMore(map));
        mv.setViewName("community/joinNotice/view_more");
        return mv;
    }

    @PostMapping("change_page") // 게시판 게시글 리스트 페이지 전환
    public ModelAndView change_page(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String member_auth = " ";
        //세션에 저장된 아이디
        if (session.getAttribute("login_id") != null) {
            String member_id = session.getAttribute("login_id").toString();
            member_auth = jservice.adminConfirmation(member_id); // 회원 관리자 여부
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("list", jservice.changePage(map));
        mv.addObject("member_auth", member_auth);
        mv.setViewName("community/joinNotice/list");
        return mv;
    }

    @PostMapping("campsite") // 캠핑장 목록 검색
    public ModelAndView campsite(@RequestBody Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", jservice.campsite(map));
        mv.setViewName("community/joinNotice/modal");
        return mv;
    }

    @PostMapping("admin_delete") // 관리자 계정 글 삭제 기능
    @ResponseBody
    public void admin_delete(HttpServletRequest request) {
        String[] delete_list = request.getParameterValues("delete_list"); // 삭제시킬 게시글 번호를 배열로 받음
        int size = delete_list.length;
        for (int i = 0; i < size; i++) { // 삭제시킬 게시글 개수만큼 삭제 진행
            int camping_id = Integer.parseInt(delete_list[i]);
            jservice.delete(camping_id);
        }
    }
}
