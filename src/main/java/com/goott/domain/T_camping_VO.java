package com.goott.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class T_camping_VO {
	private int camping_id;
	private String member_id;
	private String camping_region;
	private int camping_recruitment;
	private String camping_text;
	private String camping_close;
	private Date camping_regdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date camping_departure;
	private String camping_period;
	private int camping_hit;
	private String camping_title;
	private int camping_applicant;
	private int camping_count;
	private int reply_count;
	private String keyword;
	private String camping_campsite;
	private String campsite_lat;
	private String campsite_lon;
	private String campsite_address;
	private String camping_deleteYN;
	private int write_point;
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
	public String getCamping_region() {
		return camping_region;
	}
	public void setCamping_region(String camping_region) {
		this.camping_region = camping_region;
	}
	public int getCamping_recruitment() {
		return camping_recruitment;
	}
	public void setCamping_recruitment(int camping_recruitment) {
		this.camping_recruitment = camping_recruitment;
	}
	public String getCamping_text() {
		return camping_text;
	}
	public void setCamping_text(String camping_text) {
		this.camping_text = camping_text;
	}
	public String getCamping_close() {
		return camping_close;
	}
	public void setCamping_close(String camping_close) {
		this.camping_close = camping_close;
	}
	public Date getCamping_regdate() {
		return camping_regdate;
	}
	public void setCamping_regdate(Date camping_regdate) {
		this.camping_regdate = camping_regdate;
	}
	public Date getCamping_departure() {
		return camping_departure;
	}
	public void setCamping_departure(Date camping_departure) {
		this.camping_departure = camping_departure;
	}
	public String getCamping_period() {
		return camping_period;
	}
	public void setCamping_period(String camping_period) {
		this.camping_period = camping_period;
	}
	public int getCamping_hit() {
		return camping_hit;
	}
	public void setCamping_hit(int camping_hit) {
		this.camping_hit = camping_hit;
	}
	public String getCamping_title() {
		return camping_title;
	}
	public void setCamping_title(String camping_title) {
		this.camping_title = camping_title;
	}
	public int getCamping_applicant() {
		return camping_applicant;
	}
	public void setCamping_applicant(int camping_applicant) {
		this.camping_applicant = camping_applicant;
	}
	public int getCamping_count() {
		return camping_count;
	}
	public void setCamping_count(int camping_count) {
		this.camping_count = camping_count;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCamping_campsite() {
		return camping_campsite;
	}
	public void setCamping_campsite(String camping_campsite) {
		this.camping_campsite = camping_campsite;
	}
	public String getCampsite_lat() {
		return campsite_lat;
	}
	public void setCampsite_lat(String campsite_lat) {
		this.campsite_lat = campsite_lat;
	}
	public String getCampsite_lon() {
		return campsite_lon;
	}
	public void setCampsite_lon(String campsite_lon) {
		this.campsite_lon = campsite_lon;
	}
	public String getCampsite_address() {
		return campsite_address;
	}
	public void setCampsite_address(String campsite_address) {
		this.campsite_address = campsite_address;
	}
	public String getCamping_deleteYN() {
		return camping_deleteYN;
	}
	public void setCamping_deleteYN(String camping_deleteYN) {
		this.camping_deleteYN = camping_deleteYN;
	}
	public int getWrite_point() {
		return write_point;
	}
	public void setWrite_point(int write_point) {
		this.write_point = write_point;
	}
	@Override
	public String toString() {
		return "T_camping_VO [camping_id=" + camping_id + ", member_id=" + member_id + ", camping_region="
				+ camping_region + ", camping_recruitment=" + camping_recruitment + ", camping_text=" + camping_text
				+ ", camping_close=" + camping_close + ", camping_regdate=" + camping_regdate + ", camping_departure="
				+ camping_departure + ", camping_period=" + camping_period + ", camping_hit=" + camping_hit
				+ ", camping_title=" + camping_title + ", camping_applicant=" + camping_applicant + ", camping_count="
				+ camping_count + ", reply_count=" + reply_count + ", keyword=" + keyword + ", camping_campsite="
				+ camping_campsite + ", campsite_lat=" + campsite_lat + ", campsite_lon=" + campsite_lon
				+ ", campsite_address=" + campsite_address + ", camping_deleteYN=" + camping_deleteYN + ", write_point="
				+ write_point + "]";
	}
	
	
}
