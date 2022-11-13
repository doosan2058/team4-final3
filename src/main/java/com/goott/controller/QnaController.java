package com.goott.controller;

import com.goott.domain.PageQna;
import com.goott.domain.QnaVO;
import com.goott.service.QnaService;
import com.goott.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j
@Controller
public class QnaController {

    @Inject
    QnaService qnaService;
    @Inject
    UserService userService;
    // 질문 페이지 메인
    @RequestMapping(value = "/qna", method = RequestMethod.GET)
    public String qna(HttpServletRequest request,
                      @RequestParam(value = "clientPageNum", defaultValue = "1") int clientPageNum,
                      @RequestParam(value = "qna_category", defaultValue = "all") String qna_category,
                      @RequestParam(value = "qnaSearchText", defaultValue = "") String qnaSearchText,
                      Model model) {
        // 관리자 페이지 리다이렉트
        HttpSession session = request.getSession();
        if(session.getAttribute("login_auth") != null){
            if(session.getAttribute("login_auth").toString().equals("관리자"))
                return "redirect:/qna/admin";
        }


        // 조건에 맞는 전체 게시글 개수 초기화
        int totalCount = qnaService.totalQnaCount(qnaSearchText, qna_category);
        // 조건에 맞는 페이지 엔티티 초기화
        PageQna pageQna = new PageQna(clientPageNum, totalCount, qna_category, qnaSearchText);
        // 조건에 맞는 게시글 리스트
        List<QnaVO> list = qnaService.getQnaList(pageQna);

        model.addAttribute("pageQna", pageQna);
        model.addAttribute("list", list);


        // 일반
        return "/shop/qna/qna_list";
    }



    @RequestMapping(value = "/qna/admin")
    public String qnaAdmin(@RequestParam(value = "clientPageNum", defaultValue = "1") int clientPageNum,
                           @RequestParam(value = "qna_category", defaultValue = "all") String qna_category,
                           @RequestParam(value = "qnaSearchText", defaultValue = "") String qnaSearchText,
                           Model model){
        // 조건에 맞는 전체 게시글 개수 초기화
        int totalCount = qnaService.totalQnaCount(qnaSearchText, qna_category);

        // 조건에 맞는 페이지 엔티티 초기화
        PageQna pageQna = new PageQna(clientPageNum, totalCount, qna_category, qnaSearchText);
        // 조건에 맞는 게시글 리스트
        List<QnaVO> list = qnaService.getQnaList(pageQna);

        model.addAttribute("pageQna", pageQna);
        model.addAttribute("list", list);

        return "/shop/qna/qna_admin_list";
    }

    // 자주 묻는 질문
    @RequestMapping(value = "/qna/sample", method = RequestMethod.GET)
    public String sampleQna() {
        return "/shop/qna/sample_qna";
    }

    // qna 게시글 상세
    @RequestMapping(value = "/qna/detail", method = RequestMethod.POST)
    public String detail(@RequestParam int qna_id, @RequestParam String member_id, Model model){
        QnaVO qnaVO = qnaService.getQna(qna_id);
        String member_profile_img_url = userService.getUserImgUrl(member_id);
        log.info(member_profile_img_url);
        model.addAttribute("qnaVO", qnaVO);
        model.addAttribute("member_profile_img_url", member_profile_img_url);


        return "/shop/qna/qna_detail";
    }

    @RequestMapping(value = "/qna/add", method = RequestMethod.GET)
    public String add(){
        return "/shop/qna/qna_add";
    }

    @RequestMapping(value = "/qna/add", method = RequestMethod.POST)
    public String addPost(HttpServletRequest request, QnaVO qnaVO, @RequestParam MultipartFile file, Model model){

        String member_id = request.getSession().getAttribute("login_id").toString();

        qnaVO.setMember_id(member_id);

        String resultText = qnaService.registerQna(qnaVO, file);

        model.addAttribute("msg", resultText);
        model.addAttribute("url", "/qna");

        return "/common/alert";
    }
}