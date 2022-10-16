package com.goott.domain;

import java.util.Date;

public class T_board_VO {
	private int board_id;
	private String member_id;
	private int board_tag_id;
	private Date board_regdate;
	private String board_title;
	private String board_text;
	private int board_hit;
	private int board_recommend;
	private String board_tag_name;
	private int board_count;
	private int reply_count;
	private String board_deleteYN;
	private int write_point;
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
	public int getBoard_tag_id() {
		return board_tag_id;
	}
	public void setBoard_tag_id(int board_tag_id) {
		this.board_tag_id = board_tag_id;
	}
	public Date getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(Date board_regdate) {
		this.board_regdate = board_regdate;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_text() {
		return board_text;
	}
	public void setBoard_text(String board_text) {
		this.board_text = board_text;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public int getBoard_recommend() {
		return board_recommend;
	}
	public void setBoard_recommend(int board_recommend) {
		this.board_recommend = board_recommend;
	}
	public String getBoard_tag_name() {
		return board_tag_name;
	}
	public void setBoard_tag_name(String board_tag_name) {
		this.board_tag_name = board_tag_name;
	}
	public int getBoard_count() {
		return board_count;
	}
	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public String getBoard_deleteYN() {
		return board_deleteYN;
	}
	public void setBoard_deleteYN(String board_deleteYN) {
		this.board_deleteYN = board_deleteYN;
	}
	public int getWrite_point() {
		return write_point;
	}
	public void setWrite_point(int write_point) {
		this.write_point = write_point;
	}
	@Override
	public String toString() {
		return "T_board_VO [board_id=" + board_id + ", member_id=" + member_id + ", board_tag_id=" + board_tag_id
				+ ", board_regdate=" + board_regdate + ", board_title=" + board_title + ", board_text=" + board_text
				+ ", board_hit=" + board_hit + ", board_recommend=" + board_recommend + ", board_tag_name="
				+ board_tag_name + ", board_count=" + board_count + ", reply_count=" + reply_count + ", board_deleteYN="
				+ board_deleteYN + ", write_point=" + write_point + "]";
	}
	
	
}
