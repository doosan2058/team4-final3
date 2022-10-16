package com.goott.domain;

import java.util.Date;

public class T_camping_reply_VO {
	private String camping_reply_id;
	private int camping_id;
	private String member_id;
	private Date camping_reply_regdate;
	private String camping_reply_text;
	private int camping_reply_count;
	public String getCamping_reply_id() {
		return camping_reply_id;
	}
	public void setCamping_reply_id(String camping_reply_id) {
		this.camping_reply_id = camping_reply_id;
	}
	public int getCamping_id() {
		return camping_id;
	}
	public void setCamping_id(int camping_id) {
		this.camping_id = camping_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getCamping_reply_regdate() {
		return camping_reply_regdate;
	}
	public void setCamping_reply_regdate(Date camping_reply_regdate) {
		this.camping_reply_regdate = camping_reply_regdate;
	}
	public String getCamping_reply_text() {
		return camping_reply_text;
	}
	public void setCamping_reply_text(String camping_reply_text) {
		this.camping_reply_text = camping_reply_text;
	}
	public int getCamping_reply_count() {
		return camping_reply_count;
	}
	public void setCamping_reply_count(int camping_reply_count) {
		this.camping_reply_count = camping_reply_count;
	}
	@Override
	public String toString() {
		return "T_camping_reply_VO [camping_reply_id=" + camping_reply_id + ", camping_id=" + camping_id
				+ ", member_id=" + member_id + ", camping_reply_regdate=" + camping_reply_regdate
				+ ", camping_reply_text=" + camping_reply_text + ", camping_reply_count=" + camping_reply_count + "]";
	}
	
}
