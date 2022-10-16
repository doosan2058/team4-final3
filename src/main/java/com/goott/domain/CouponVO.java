package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CouponVO {
	/**
	 * coupon_num varchar(16) primary key comment "쿠폰번호(16자)",
	   coupon_name varchar(20) not null comment "쿠폰이름",
	   coupon_comment varchar(50) not null comment "쿠폰설명(50자)",
	   coupon_discount double not null comment "할인율",
	   coupon_category int default 0 comment "할인 대상 1 (카테고리 아이디), 0 : all",
	   coupon_brand int default 0 comment "할인 대상 2 (브랜드 아이디), 0 : all",
	   coupon_live varchar(3) default "미사용" comment "사용 여부(사용/미사용)",
	   coupon_start_date datetime not null comment "쿠폰 시작 날짜",
	   coupon_end_date datetime not null comment "쿠폰 종료 날짜"
	 */
	
	private String coupon_num;
	private String coupon_name;
	private String coupon_comment;
	private double coupon_discount;
	private int coupon_category;
	private int coupon_brand;
	private String coupon_live;
	private Date coupon_start_date;
	private Date coupon_end_date;
}
