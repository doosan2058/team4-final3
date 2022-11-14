package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerVO {
    int answer_id;
    int qna_id;
    String answer_text;
    Date answer_regdate;
    Date answer_update_date;

}
