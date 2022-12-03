package com.goott.domain;

import lombok.Data;

@Data
public class PageShop {
	private int startNum; //상품 검색 시작 점
	private int endNum; //상품 검색 끝점
	private int currentPage; //현재 페이지
	private int totalPage; //전체 페이지 수
	private int category_id; //카테고리 아이디 
	private int brand_id; //브랜드 아이디
	private String searchOption;
	public PageShop(){
		
	}

	public PageShop(int currentPage, int totalPage, String searchOption) {
		this.startNum = (currentPage - 1) * 10;
		this.endNum = 10;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.searchOption = searchOption;
	}
	
	
	
	
	
	
}
