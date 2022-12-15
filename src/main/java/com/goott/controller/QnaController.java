package com.goott.controller;

import com.goott.domain.AnswerVO;
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

    /**
     * 질문 게시판 페이지 요청
     *
     * @param request
     * @param clientPageNum 질문 게시판 페이지 번호(초기값 1)
     * @param qna_category  질문 게시판 카테고리(초기값 all)
     * @param qnaSearchText 질문 게시판 검색어(초기값 "")
     * @param model         질문글 리스트(10개), 질문 게시판 페이지 엔티티
     * @return 질문 게시판 페이지
     */
    @RequestMapping(value = "/qna", method = RequestMethod.GET)
    public String qna(HttpServletRequest request,
                      @RequestParam(value = "clientPageNum", defaultValue = "1") int clientPageNum,
                      @RequestParam(value = "qna_category", defaultValue = "all") String qna_category,
                      @RequestParam(value = "qnaSearchText", defaultValue = "") String qnaSearchText,
                      Model model) {
        // 관리자 페이지 리다이렉트
        HttpSession session = request.getSession();
        if (session.getAttribute("login_auth") != null) { // 관리자 접근이라면
            if (session.getAttribute("login_auth").toString().equals("관리자"))
                return "redirect:/qna/admin";
        }

        // 조건에 맞는 전체 게시글 개수 초기화
        int totalCount = qnaService.totalQnaCount(qnaSearchText, qna_category);
        // 조건에 맞는 질문 게시판 페이지 엔티티 초기화
        PageQna pageQna = new PageQna(clientPageNum, totalCount, qna_category, qnaSearchText);
        // 조건에 맞는 게시글 리스트(10개)
        List<QnaVO> list = qnaService.getQnaList(pageQna);
        model.addAttribute("pageQna", pageQna);
        model.addAttribute("list", list);

        return "/shop/qna/qna_list";
    }

    /**
     * 관리자용 질문 게시판 페이지 요청
     *
     * @param clientPageNum    질문 게시판 페이지 번호(초기값 1)
     * @param qna_category     질문 게시판 카테고리(초기값 all)
     * @param qnaSearchText    질문 게시판 검색어(초기값 "")
     * @param qna_admin_answer 관리자 답변 작성 여부(초기값 all)
     * @param model            질문글 리스트(10개), 질문 게시판 페이지 엔티티
     * @return 관리자용 질문 게시판 페이지
     */
    @RequestMapping(value = "/qna/admin", method = RequestMethod.GET)
    public String qnaAdmin(@RequestParam(value = "clientPageNum", defaultValue = "1") int clientPageNum,
                           @RequestParam(value = "qna_category", defaultValue = "all") String qna_category,
                           @RequestParam(value = "qnaSearchText", defaultValue = "") String qnaSearchText,
                           @RequestParam(value = "qna_admin_answer", defaultValue = "all") String qna_admin_answer,
                           Model model) {
        // 조건에 맞는 전체 게시글 개수 초기화
        int totalCount = qnaService.countQnaAdmin(qnaSearchText, qna_category, qna_admin_answer);
        // 조건에 맞는 페이지 엔티티 초기화
        PageQna pageQna = new PageQna(clientPageNum, totalCount, qna_category, qnaSearchText);
        // 답변 여부 초기화
        pageQna.setQna_admin_answer(qna_admin_answer);
        // 조건에 맞는 게시글 리스트
        List<QnaVO> list = qnaService.selectListAdmin(pageQna);
        model.addAttribute("pageQna", pageQna);
        model.addAttribute("list", list);

        return "/shop/qna/qna_list_admin";
    }

    /**
     * 자주 묻는 질문 페이지 요청
     *
     * @return 자주 묻는 질문 페이지
     */
    @RequestMapping(value = "/qna/sample", method = RequestMethod.GET)
    public String sampleQna() {
        return "/shop/qna/sample_qna";
    }

    /**
     * 질문 게시글 상세 페이지 요청
     *
     * @param qna_id 게시글 번호
     * @param model  질문 정보, 답변 정보, 작성자 프로필 이미지 주소
     * @return 질문 게시글 상세 페이지
     */
    @RequestMapping(value = "/qna/detail", method = RequestMethod.GET)
    public String detail(@RequestParam int qna_id, Model model) {
        QnaVO qnaVO = qnaService.getQna(qna_id);
        String member_profile_img_url = userService.getUserImgUrl(qnaVO.getMember_id());

        // 괸리자가 답변을 작성 하였다면
        if (qnaVO.getQna_admin_answer().equals("y")) {
            AnswerVO answerVO = qnaService.getAnswer(qna_id);
            model.addAttribute("answerVO", answerVO); // 답변 정보
        }

        model.addAttribute("qnaVO", qnaVO); // 질문 정보
        model.addAttribute("member_profile_img_url", member_profile_img_url); // 작성자 프로필 이미지 주소

        return "/shop/qna/qna_detail";
    }

    /**
     * 관리자용 질문 게시글 상세 페이지 요청
     *
     * @param qna_id 질문 게시글 번호
     * @param model  질문 엔티티, 답변 엔티티, 작성자 프로필 이미지 주소
     * @return 관리자용 질문 게시글 상세 페이지
     */
    @RequestMapping(value = "/qna/detail/admin", method = RequestMethod.GET)
    public String detailAdmin(@RequestParam int qna_id, Model model) {
        QnaVO qnaVO = qnaService.getQna(qna_id);
        String member_profile_img_url = userService.getUserImgUrl(qnaVO.getMember_id());

        // 괸리자가 답변을 작성 하였다면
        if (qnaVO.getQna_admin_answer().equals("y")) {
            AnswerVO answerVO = qnaService.getAnswer(qna_id);
            model.addAttribute("answerVO", answerVO); // 답변 정보
        }

        model.addAttribute("qnaVO", qnaVO); // 질문 정보
        model.addAttribute("member_profile_img_url", member_profile_img_url); // 작성자 프로필 이미지 주소

        return "/shop/qna/qna_detail_admin";
    }

    /**
     * 질문 게시글 등록 페이지 요청
     *
     * @return 질문 게시글 등록 페이지
     */
    @RequestMapping(value = "/qna/add", method = RequestMethod.GET)
    public String add() {
        return "/shop/qna/qna_add";
    }

    /**
     * 질문 게시글 등록
     *
     * @param request
     * @param qnaVO   질문 게시글 레코드
     * @param file    첨부파일(이미지)
     * @param model   질문 게시글 등록 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "/qna/add", method = RequestMethod.POST)
    public String addPost(HttpServletRequest request, QnaVO qnaVO, @RequestParam MultipartFile file, Model model) {

        String member_id = request.getSession().getAttribute("login_id").toString(); // 작성자 아이디
        qnaVO.setMember_id(member_id);
        String resultText = qnaService.registerQna(qnaVO, file); // 질문 게시글 등록 처리
        model.addAttribute("msg", resultText);
        model.addAttribute("url", "/qna");

        return "/common/alert";
    }

    /**
     * 질문 게시글 수정 페이지 요청
     *
     * @param qna_id 수정할 질문 게시글 번호
     * @param model  질문 엔티티
     * @return 질문 게시글 수정 페이지
     */
    @RequestMapping(value = "/qna/modify", method = RequestMethod.GET)
    public String modify(@RequestParam int qna_id, Model model) {

        QnaVO qnaVO = qnaService.getQna(qna_id);
        model.addAttribute("qnaVO", qnaVO); // 질문 게시글 레코드
        return "/shop/qna/qna_modify";


    }

    /**
     * 질문글 수정 처리
     *
     * @param model 수정 처리 결과
     * @param qnaVO 질문글 수정 엔티티
     * @param file  새로 저장할 첨부파일(이미지)
     * @return 알람창
     */
    @RequestMapping(value = "/qna/modify", method = RequestMethod.POST)
    public String modifyPost(Model model, QnaVO qnaVO, MultipartFile file) {
        if (file.isEmpty()) // 첨부파일이 없는 게시글 초기화
            qnaVO.setQna_picture_url("not url");

        qnaService.updateQna(qnaVO, file); // 기존 게시글 정보 업데이트, 기존 첨부파일 삭제 및 새 첨부파일 저장
        int qna_id = qnaVO.getQna_id();
        model.addAttribute("msg", "질문글을 수정 하였습니다.");
        model.addAttribute("url", "/qna/detail?qna_id=" + qna_id);
        return "/common/alert";
    }

    /**
     * 질문글 삭제 처리
     *
     * @param qna_id 질문글 번호
     * @param model  질문글 삭제 처리 결과
     * @return 알람창
     */
    @RequestMapping(value = "/qna/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam int qna_id, Model model) {
        qnaService.delete(qna_id); // 삭제 처리
        model.addAttribute("msg", "질문글을 삭제 하였습니다.");
        model.addAttribute("url", "/qna");
        return "/common/alert";
    }

    /**
     * 관리자용 답변 등록 처리
     *
     * @param answerVO 답변 엔티티
     * @param model    답변 등록 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "/qna/answer/register", method = RequestMethod.POST)
    public String answerRegisterPost(AnswerVO answerVO, Model model) {
        qnaService.registerAnswer(answerVO);
        int qna_id = answerVO.getQna_id();
        model.addAttribute("msg", "답변이 등록 되었습니다.");
        model.addAttribute("url", "/qna/detail/admin?qna_id=" + qna_id);
        return "/common/alert";
    }

    /**
     * 관리자 답변 수정 처리
     *
     * @param answerVO 답변 엔티티
     * @param model    다변 수정 처리 결과
     * @return
     */
    @RequestMapping(value = "/qna/answer/modify", method = RequestMethod.POST)
    public String answerModifyPost(AnswerVO answerVO, Model model) {
        qnaService.updateAnswer(answerVO); // 답변 수정
        int qna_id = answerVO.getQna_id();
        model.addAttribute("msg", "답변을 수정 하였습니다.");
        model.addAttribute("url", "/qna/detail/admin?qna_id=" + qna_id);
        return "/common/alert";
    }

    /**
     * 관리자 답변 삭제 처리
     *
     * @param answerVO 답변 엔티티
     * @param model    답변 삭제 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "/qna/answer/delete", method = RequestMethod.POST)
    public String answerDeletePost(AnswerVO answerVO, Model model) {
        qnaService.deleteAnswerAdmin(answerVO);
        int qna_id = answerVO.getQna_id();
        model.addAttribute("msg", "답변을 삭제 하였습니다.");
        model.addAttribute("url", "/qna/detail/admin?qna_id=" + qna_id);
        return "/common/alert";
    }

}