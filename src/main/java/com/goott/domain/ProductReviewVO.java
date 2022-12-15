package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReviewVO {
	private int product_review_id; // 리뷰 번호
	private int product_id; // 상품 번호
	private String member_id; // 작성자 아이디
	private Date product_review_regdate; // 리뷰 등록일
	private String product_review_text; // 리뷰 내용
	private int product_review_grade; // 평점
	private int product_review_helpful; // 리뷰 추천수
	private String product_review_img_url; // 첨부파일 이미지 주소
	private String product_review_video_url; // 첨부파일 동영상 주소
	private String product_review_speed; // 배송 속도
	private String member_profile_img_url; // 작성자 프로필 이미지 주소
}
