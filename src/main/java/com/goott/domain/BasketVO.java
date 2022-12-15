package com.goott.domain;

import lombok.Data;

@Data
public class BasketVO {
	private int basket_id; // 장바구니 번호
	private String member_id; // 회원 아이디
	private int product_id; // 상품 번호
	private int basket_amount; // 상품 개수
	private String product_name; // 상품 이름
	private String product_img_url1; // 상품 이미지 주소
}
