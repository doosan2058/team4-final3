package com.goott.domain;

import java.util.Date;

public class DrawResultVO {
	
	private int draw_result_id;
	private int draw_id;
	private String member_id;
	private Date draw_result_regdate;
	private String draw_result_winning;
	public int getDraw_result_id() {
		return draw_result_id;
	}
	public void setDraw_result_id(int draw_result_id) {
		this.draw_result_id = draw_result_id;
	}
	public int getDraw_id() {
		return draw_id;
	}
	public void setDraw_id(int draw_id) {
		this.draw_id = draw_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getDraw_result_regdate() {
		return draw_result_regdate;
	}
	public void setDraw_result_regdate(Date draw_result_regdate) {
		this.draw_result_regdate = draw_result_regdate;
	}
	public String getDraw_result_winning() {
		return draw_result_winning;
	}
	public void setDraw_result_winning(String draw_result_winning) {
		this.draw_result_winning = draw_result_winning;
	}
	@Override
	public String toString() {
		return "DrawResultVO [draw_result_id=" + draw_result_id + ", draw_id=" + draw_id + ", member_id=" + member_id
				+ ", draw_result_regdate=" + draw_result_regdate + ", draw_result_winning=" + draw_result_winning + "]";
	}

}
