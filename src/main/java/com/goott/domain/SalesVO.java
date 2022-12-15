package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SalesVO {
	private int sales_id; // 매출 번호
	private int order_id; // 주문 번호
	private Date sales_date; // 매출 확정일
	private String sales_review; // 리뷰글 작성 여부
	private Date sales_review_date; // 리뷰글 작성일
	
}
