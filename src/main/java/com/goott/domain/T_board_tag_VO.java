package com.goott.domain;

public class T_board_tag_VO {
	
	private int board_tag_id;
	private String board_tag_name;
	
	public int getBoard_tag_id() {
		return board_tag_id;
	}
	public void setBoard_tag_id(int board_tag_id) {
		this.board_tag_id = board_tag_id;
	}
	public String getBoard_tag_name() {
		return board_tag_name;
	}
	public void setBoard_tag_name(String board_tag_name) {
		this.board_tag_name = board_tag_name;
	}
	
	@Override
	public String toString() {
		return "T_board_tag_VO [board_tag_id=" + board_tag_id + ", board_tag_name=" + board_tag_name + "]";
	}
	
	
}
