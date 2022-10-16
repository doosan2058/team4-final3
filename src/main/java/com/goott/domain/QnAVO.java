package com.goott.domain;

import java.util.Date;

public class QnAVO {
	
	
	private int qna_id;	//qna 아이디
	private String member_id;	// member 아이디
	private String qna_category;	// category 아이디
	private String qna_public;	// qna 공개여부 
	private String qna_title;	// qna 제목
	private String qna_text;	// qna 내용
	private Date qna_regdate;	// qna 작성일
	private String qna_admin_answer;	// 관리자 답변 여부
	
	
	private int qna_answer_id;	// 답변 아이디
	private String qna_answer_text;	// 답변 내
	private Date qna_answer_regdate; // 관리자 답변 작성
	
	
	@Override
	public String toString() {
		return "QnAVO [qna_id=" + qna_id + ", member_id=" + member_id + ", qna_category=" + qna_category
				+ ", qna_public=" + qna_public + ", qna_title=" + qna_title + ", qna_text=" + qna_text
				+ ", qna_regdate=" + qna_regdate + ", qna_admin_answer=" + qna_admin_answer + ", qna_answer_id="
				+ qna_answer_id + ", qna_answer_text=" + qna_answer_text + ", qna_answer_regdate=" + qna_answer_regdate
				+ "]";
	}


	public int getQna_id() {
		return qna_id;
	}


	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getQna_category() {
		return qna_category;
	}


	public void setQna_category(String qna_category) {
		this.qna_category = qna_category;
	}


	public String getQna_public() {
		return qna_public;
	}


	public void setQna_public(String qna_public) {
		this.qna_public = qna_public;
	}


	public String getQna_title() {
		return qna_title;
	}


	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}


	public String getQna_text() {
		return qna_text;
	}


	public void setQna_text(String qna_text) {
		this.qna_text = qna_text;
	}


	public Date getQna_regdate() {
		return qna_regdate;
	}


	public void setQna_regdate(Date qna_regdate) {
		this.qna_regdate = qna_regdate;
	}


	public String getQna_admin_answer() {
		return qna_admin_answer;
	}


	public void setQna_admin_answer(String qna_admin_answer) {
		this.qna_admin_answer = qna_admin_answer;
	}


	public int getQna_answer_id() {
		return qna_answer_id;
	}


	public void setQna_answer_id(int qna_answer_id) {
		this.qna_answer_id = qna_answer_id;
	}


	public String getQna_answer_text() {
		return qna_answer_text;
	}


	public void setQna_answer_text(String qna_answer_text) {
		this.qna_answer_text = qna_answer_text;
	}


	public Date getQna_answer_regdate() {
		return qna_answer_regdate;
	}


	public void setQna_answer_regdate(Date qna_answer_regdate) {
		this.qna_answer_regdate = qna_answer_regdate;
	}
	
	
	
	
	
}
