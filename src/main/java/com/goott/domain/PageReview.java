package com.goott.domain;

import lombok.Data;

@Data
public class PageReview {
	private int searchCount; // 한 페이지 리뷰글 개수(10개)
	private int totalNum; // 전체 리뷰글 개수
	private int currentPage; // 현재 리뷰 페이지 번호
	private int lastPage; // 마지막 페이지 번호
	private int searchStartNum; // 출력할 리뷰글 시작 번호
	private int product_id;
	private boolean end; // 마지막 페이지 여부
	
	public PageReview(int currentPage, int totalNum) {
		this.searchCount = 10;
		this.totalNum = totalNum;
		this.currentPage = currentPage;
		//전체 게시글 개수가 10개보다 작으면 끝 페이지는 무조건 1
		if(totalNum < searchCount)
			this.lastPage = 1;
		else
			this.lastPage = (int) Math.ceil( ( (totalNum * 1.0) / searchCount ) );  
		this.searchStartNum = ( currentPage - 1 ) * 10;
		
		//현재 페이지가 끝페이지면 더보기 숨기기
		this.end = ( currentPage == lastPage ) ? true : false;
		
	}
}
