package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReviewVO {
	int product_review_id;
	int product_id;
	String member_id;
	Date product_review_regdate;
	String product_review_text;
	int product_review_grade;
	int product_review_helpful;
	String product_review_img_url;
	String product_review_video_url;
	String product_review_speed;
	String member_profile_img_url;
}
