package com.goott.domain;

import java.util.Date;

public class T_board_reply_VO {
	private int board_reply_id;
	private int board_id;
	private String member_id;
	private Date board_reply_regdate;
	private String board_reply_public;
	private String board_reply_text;
	private int board_reply_count;

	public int getBoard_reply_id() {
		return board_reply_id;
	}
	public void setBoard_reply_id(int board_reply_id) {
		this.board_reply_id = board_reply_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getBoard_reply_regdate() {
		return board_reply_regdate;
	}
	public void setBoard_reply_regdate(Date board_reply_regdate) {
		this.board_reply_regdate = board_reply_regdate;
	}
	public String getBoard_reply_public() {
		return board_reply_public;
	}
	public void setBoard_reply_public(String board_reply_public) {
		this.board_reply_public = board_reply_public;
	}
	public String getBoard_reply_text() {
		return board_reply_text;
	}
	public void setBoard_reply_text(String board_reply_text) {
		this.board_reply_text = board_reply_text;
	}

	public int getBoard_reply_count() {
		return board_reply_count;
	}
	public void setBoard_reply_count(int board_reply_count) {
		this.board_reply_count = board_reply_count;
	}
	
	@Override
	public String toString() {
		return "T_board_reply_VO [board_reply_id=" + board_reply_id + ", board_id=" + board_id + ", member_id="
				+ member_id + ", board_reply_regdate=" + board_reply_regdate + ", board_reply_public="
				+ board_reply_public + ", board_reply_text=" + board_reply_text + ", board_reply_count="
				+ board_reply_count + "]";
	}
	
	
	
}
