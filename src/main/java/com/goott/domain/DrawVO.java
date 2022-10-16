package com.goott.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;




@Data
public class DrawVO {
	
	public int draw_id;
	public String draw_title;
	public int product_id;
	public int draw_price;
   @DateTimeFormat(pattern="yyyy-MM-dd")
	public Date draw_event_start_date;
   @DateTimeFormat(pattern="yyyy-MM-dd")
	public Date draw_event_end_date;
	public String draw_comment;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	public Date draw_pur_start_date;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	public Date draw_pur_end_date;
	public int draw_reqruit;
	public String product_img_url1;
	public String draw_deadline;
	  
	
//	public String getProduct_img_url1() {
//		return product_img_url1;
//	}
//	public void setProduct_img_url1(String product_img_url1) {
//		this.product_img_url1 = product_img_url1;
//	}
//	public int getDraw_id() {
//		return draw_id;
//	}
//	public void setDraw_id(int draw_id) {
//		this.draw_id = draw_id;
//	}
//	public String getDraw_title() {
//		return draw_title;
//	}
//	public void setDraw_title(String draw_title) {
//		this.draw_title = draw_title;
//	}
//	public int getProduct_id() {
//		return product_id;
//	}
//	public void setProduct_id(int product_id) {
//		this.product_id = product_id;
//	}
//	public int getDraw_price() {
//		return draw_price;
//	}
//	public void setDraw_price(int draw_price) {
//		this.draw_price = draw_price;
//	}
//	public Date getDraw_event_start_date() {
//		return draw_event_start_date;
//	}
//	public void setDraw_event_start_date(Date draw_event_start_date) {
//		this.draw_event_start_date = draw_event_start_date;
//	}
//	public Date getDraw_event_end_date() {
//		return draw_event_end_date;
//	}
//	public void setDraw_event_end_date(Date draw_event_end_date) {
//		this.draw_event_end_date = draw_event_end_date;
//	}
//	public String getDraw_comment() {
//		return draw_comment;
//	}
//	public void setDraw_comment(String draw_comment) {
//		this.draw_comment = draw_comment;
//	}
//	public Date getDraw_pur_start_date() {
//		return draw_pur_start_date;
//	}
//	public void setDraw_pur_start_date(Date draw_pur_start_date) {
//		this.draw_pur_start_date = draw_pur_start_date;
//	}
//	public Date getDraw_pur_end_date() {
//		return draw_pur_end_date;
//	}
//	public void setDraw_pur_end_date(Date draw_pur_end_date) {
//		this.draw_pur_end_date = draw_pur_end_date;
//	}
//	public int getDraw_reqruit() {
//		return draw_reqruit;
//	}
//	public void setDraw_reqruit(int draw_reqruit) {
//		this.draw_reqruit = draw_reqruit;
//	}
//	
//	
//	@Override
//	public String toString() {
//		return "DrawVO [draw_id=" + draw_id + ", draw_title=" + draw_title + ", product_id=" + product_id
//				+ ", draw_price=" + draw_price + ", draw_event_start_date=" + draw_event_start_date
//				+ ", draw_event_end_date=" + draw_event_end_date + ", draw_comment=" + draw_comment
//				+ ", draw_pur_start_date=" + draw_pur_start_date + ", draw_pur_end_date=" + draw_pur_end_date
//				+ ", draw_reqruit=" + draw_reqruit + "]";
//	}
//	public String getDraw_deadline() {
//		return draw_deadline;
//	}
//	public void setDraw_deadline(String draw_deadline) {
//		this.draw_deadline = draw_deadline;
//	}
//	
	
	
}
