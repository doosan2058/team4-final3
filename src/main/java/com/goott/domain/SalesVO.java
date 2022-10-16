package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SalesVO {
	int sales_id;
	int order_id;
	Date sales_date;
	String sales_review;
	Date sales_review_date;
	
}
