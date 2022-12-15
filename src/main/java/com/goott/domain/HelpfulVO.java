package com.goott.domain;

import lombok.Data;

@Data
public class HelpfulVO {
	private int helpful_id; // 리뷰 추천 번호
	private int product_review_id; // 리뷰 번호
	private String member_id; // 추천인 아이디
}
