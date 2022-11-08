package com.goott.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j
@Controller
@RequestMapping(value = ("/qna/"))
public class QnaController {

    // 자주 묻는 질문
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String sampleQna() {
        return "/shop/qna/sample_qna";
    }
}