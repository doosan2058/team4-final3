package com.goott.domain;

import java.util.Date;

public class DrawEnterVO {
	
	private int draw_enter_id;
	private int draw_id;
	private String member_id;
	private Date draw_enter_regdate;
	
	public int getDraw_enter_id() {
		return draw_enter_id;
	}
	public void setDraw_enter_id(int draw_enter_id) {
		this.draw_enter_id = draw_enter_id;
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
	public Date getDraw_enter_regdate() {
		return draw_enter_regdate;
	}
	public void setDraw_enter_regdate(Date draw_enter_regdate) {
		this.draw_enter_regdate = draw_enter_regdate;
	}
	
	@Override
	public String toString() {
		return "DrawEnter [draw_enter_id=" + draw_enter_id + ", draw_id=" + draw_id + ", member_id=" + member_id
				+ ", draw_enter_regdate=" + draw_enter_regdate + "]";
	}
	

}
