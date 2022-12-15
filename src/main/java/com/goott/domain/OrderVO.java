package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {

	int order_id; // 주문 번호
	String member_id; // 회원 아이디
	int product_id; // 상품 번호
	Date order_start_date; // 주문 시작일
	Date order_end_date; // 주문 도착 예상일
	int order_quantity; // 주문 수량
	String order_address; // 주소
	int order_purchase_amount; // 주문 금액
	String order_state; // 배송 상태
	String order_coupon_num; // 사용 쿠폰 번호
	String order_comment; // 배송 메시지
	int member_purchase_point; // 적립 예정 포인트
	
	
	
}
