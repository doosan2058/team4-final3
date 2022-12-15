package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerVO {
    private int answer_id; // 답변 번호
    private int qna_id; // 질문 번호
    private String answer_text; // 답변 내용
    private Date answer_regdate; // 답변 등록일
    private Date answer_update_date; // 답변 수정일

}
