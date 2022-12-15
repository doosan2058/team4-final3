package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class QnaVO {
    private int qna_id; // 질문 번호
    private String member_id; // 작성자 아이디
    private String qna_category; // 카테고리
    private String qna_public; // 공개 여부
    private String qna_title; // 제목
    private String qna_text; // 내용
    private String qna_picture_url; // 첨부파일 이미지 주소
    private Date qna_regdate; // 등록일
    private Date qna_update_date; // 수정일
    private String qna_admin_answer; // 관리자 답변 여부
    private String qna_delete; // 질문글 삭제 여부

}
