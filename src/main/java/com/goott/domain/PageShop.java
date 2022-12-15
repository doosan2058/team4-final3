package com.goott.domain;

import lombok.Data;

@Data
public class PageShop {
	private int startNum; // 상품 검색 시작 번호
	private int endNum; // 상품 출력 개수(10개)
	private int currentPage; // 현재 페이지 번호
	private int totalPage; // 전체 페이지 수
	private int category_id; // 카테고리 아이디
	private int brand_id; // 브랜드 아이디
	private String searchOption; // 검색 옵션
	public PageShop(){}

	public PageShop(int currentPage, int totalPage, String searchOption) {
		this.startNum = (currentPage - 1) * 10;
		this.endNum = 10;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.searchOption = searchOption;
	}
	
	
	
	
	
	
}
