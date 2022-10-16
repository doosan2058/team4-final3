package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	/**
	 *  order_id int unsigned primary key auto_increment comment "주문 번호 pk",
		member_id varchar(20),
		product_id int unsigned,
		order_start_date datetime default now() comment "주문 시작일",
		order_end_date datetime comment "상품 수령일",
		order_quantity int unsigned default 1 comment "주문 수량",
		order_address varchar(200) not null comment "주문 배송지",
		order_purchase_amount int unsigned comment "주문 금액",
		order_state varchar(5) default "배송중" comment "주문 상태(배송중/배송완료/구매취소/구매확정)",
		order_coupon_num varchar(16) not null comment "사용한 쿠폰 번호",
		order_comment varchar(500) not null comment "배송 요청 사항", 
		member_purchase_point int "적입 예정 포인트"
	 */
	
	int order_id;
	String member_id;
	int product_id;
	Date order_start_date;
	Date order_end_date;
	int order_quantity;
	String order_address;
	int order_purchase_amount;
	String order_state;
	String order_coupon_num;
	String order_comment;
	int member_purchase_point;
	
	
	
}
