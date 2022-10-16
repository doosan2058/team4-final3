package com.goott.domain;

import lombok.Data;

@Data
public class BasketVO {
	int basket_id;
	String member_id;
	int product_id;
	int basket_amount;
}
