package com.goott.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goott.domain.PagingVO;
import com.goott.domain.QnAVO;
import com.goott.service.QnAService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = ("/shop/"))
public class QnAController {

    @Autowired
    QnAService qnaservice;

    // 게시물 조회 , 목록
    @RequestMapping(value = "/qna")
    public String QnA(PagingVO vo, Model model, @RequestParam(value = "nowPage", required = false) String nowPage,
                      @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                      @RequestParam(defaultValue = "all") String checkcategory,
                      @RequestParam(defaultValue = "all") String QnASearch, QnAVO qnA, HttpServletRequest request) {

        //만약 관리자 아이디로 로그인 했다면 질문 게시판 관리자 페이지로 이동
        //세션 불러오기
        HttpSession session = request.getSession();
        //세션 권한 확인
        if (session.getAttribute("login_id") != null && session.getAttribute("login_auth").toString().equals("관리자")) {
            return "redirect:/shop/qna_admin";
        }


        int total = qnaservice.countQnABoardCategory(checkcategory, QnASearch);


        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }

        vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        List<QnAVO> data = qnaservice.selectQnABoard(vo, checkcategory, QnASearch);
        model.addAttribute("paging", vo);
        model.addAttribute("list", data);
        model.addAttribute("checkcategory", checkcategory);
        model.addAttribute("QnASearch", QnASearch);


        return "shop/qna/qna";
    }

    // 게시물 작성
    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public String Question() {

        return "/shop/qna/question";
    }

    // 게시물 작성
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public ModelAndView Question(QnAVO QnA) {

        if (QnA.getQna_text().equals("")) {
            QnA.setQna_text("내용이 없습니다.");
        }
        qnaservice.Question(QnA);



        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/shop/qna");
        return mv;
    }

    // 게시물 상세페이지
    @RequestMapping(value = "/qna_detail", method = RequestMethod.GET)
    public ModelAndView Question_detail(@RequestParam Map<String, Object> map, QnAVO QnA) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/shop/qna/qna_detail");
        mv.addObject("data", qnaservice.QnA_detail(map));

        return mv;
    }

    // 게시물 수정
    @RequestMapping(value = "/question_update", method = RequestMethod.GET)
    public String QnA_update(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("data", qnaservice.QnA_detail(map));
        return "shop/qna/question_update";
    }

    // 게시물 수정
    @RequestMapping(value = "/question_update", method = RequestMethod.POST)
    public ModelAndView QnA_update(@RequestParam Map<String, Object> map, QnAVO QnA) {

        ModelAndView mv = new ModelAndView();
        qnaservice.Question_update(QnA);
        mv.setViewName("redirect:/shop/qna");
        return mv;
    }

    // 게시물 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(QnAVO QnA) {

        qnaservice.delete(QnA);
        return "redirect:/shop/qna";
    }

    // 게시물 답변
    @RequestMapping(value = "/question_admin", method = RequestMethod.GET)
    public String Quetion_admin(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("data", qnaservice.QnA_detail(map));

        return "shop/qna/question_admin";
    }

    // 게시물 답변
    @RequestMapping(value = "/question_admin", method = RequestMethod.POST)
    public ModelAndView Question_admin(@RequestParam Map<String, Object> map, QnAVO QnA) {
        ModelAndView mv = new ModelAndView();
        if (QnA.getQna_answer_text().equals("")) {
            QnA.setQna_answer_text("이용해주셔서 감사합니다.");
        }
        qnaservice.Question_admin(QnA);
        qnaservice.Question_admin_answer(QnA);

        mv.setViewName("redirect:/shop/qna");
        return mv;
    }

    // 자주 묻는 질문
    @RequestMapping(value = "/asked_question", method = RequestMethod.GET)
    public String asked_Question() {

        return "shop/qna/asked_question";
    }

    // 관리자 페이지
    @RequestMapping(value = "/qna_admin")
    public String QnA_admin(PagingVO vo, Model model, @RequestParam(value = "nowPage", required = false) String nowPage,
                            @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
                            @RequestParam(defaultValue = "all") String checkadmin, @RequestParam(defaultValue = "all") String QnASearch,
                            QnAVO qna) {

        int total = qnaservice.countQnAadminBoardCategory(checkadmin, QnASearch);


        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }

        vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

        List<QnAVO> data = qnaservice.selectQnAadminBoard(vo, checkadmin, QnASearch);
        model.addAttribute("paging", vo);
        model.addAttribute("list", data);
        model.addAttribute("checkadmin", checkadmin);
        model.addAttribute("QnASearch", QnASearch);


        return "shop/qna/qna_admin";
    }


    // 게시물 답변 수정
    @RequestMapping(value = "/question_admin_update", method = RequestMethod.GET)
    public String Question_admin_update(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("data", qnaservice.QnA_detail(map));
        return "shop/qna/question_admin_update";
    }

    // 게시물 답변 수정
    @RequestMapping(value = "/question_admin_update", method = RequestMethod.POST)
    public ModelAndView Question_admin_update(@RequestParam Map<String, Object> map, QnAVO QnA) {

        ModelAndView mv = new ModelAndView();
        if (QnA.getQna_answer_text().equals("")) {
            QnA.setQna_answer_text("이용해주셔서 감사합니다.");
        }
        qnaservice.Question_admin_update(QnA);
        mv.setViewName("redirect:/shop/qna_admin");

        return mv;
    }


}