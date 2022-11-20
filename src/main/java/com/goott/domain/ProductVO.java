package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
	private int product_id; //상품 pk
	private int product_category_id; //상품 카테고리 FK
	private int product_brand_id; //상품 브랜드 FK
	private String product_limited; //한정판 여부(일반/한정판)
	private int product_stock; //재고
	private String product_name; //상품 이름
	private String product_comment; //상품 설명
	private int product_price; //가격
	private String product_thumbnail_img_url;
	private String product_img_url1; //상품 이미지1
	private String product_img_url2; //상품 이미지2
	private String product_img_url3; //상품 이미지3
	private String product_description_img_url1; //설명 이미지1
	private String product_description_img_url2; //설명 이미지2
	private String product_youtube_url; //유튜브 주소
	private Date product_regdate; //상품 등록일
	private int product_delivery_day; //상품 배송기간
	private String product_delete; //상품 공개 여부(y/n)
	private String product_category_name;
	private String product_brand_name;
	private int product_sales_rate;
	
	//주소 입력 안했을시 초기화
	public ProductVO() {
		this.setProduct_thumbnail_img_url("no url");
		this.setProduct_img_url1("no url");
		this.setProduct_img_url2("no url");
		this.setProduct_img_url3("no url");
		this.setProduct_description_img_url1("no url");
		this.setProduct_description_img_url2("no url");
		
		
	}
	
}
