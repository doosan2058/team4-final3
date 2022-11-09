package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class QnaVO {
    int qna_id;
    String member_id;
    String qna_category;
    String qna_public;
    String qna_title;
    String qna_text;
    String qna_picture_url;
    Date qna_regdate;
    Date qna_update_date;
    String qna_admin_answer;
    String qna_delete;

}
