package com.goott.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Log4j
@Controller
public class QnaController {
    // 질문 페이지 메인
    @RequestMapping(value = "/qna", method = RequestMethod.GET)
    public String qna(HttpServletRequest request){
        // 관리자 페이지 리다이렉트

        // 일반
        return "/shop/qna/qna_list";
    }
    // 자주 묻는 질문
    @RequestMapping(value = "/qna/sample", method = RequestMethod.GET)
    public String sampleQna() {
        return "/shop/qna/sample_qna";
    }
}