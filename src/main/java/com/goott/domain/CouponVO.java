package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CouponVO {

	
	private String coupon_num; // 쿠폰 번호
	private String coupon_name; // 쿠폰 이름
	private String coupon_comment; // 쿠폰 설명
	private double coupon_discount; // 쿠폰 할인율
	private int coupon_category; // 사용 가능한 상품 카테고리 번호
	private int coupon_brand; // 사용 가능한 상품 브랜드 번호
	private String coupon_live; // 쿠폰 사용 여부
	private Date coupon_start_date; // 쿠폰 사용 가능 시작일
	private Date coupon_end_date; // 쿠폰 사용 가능 종료일
}
