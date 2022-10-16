package com.goott.domain;

import lombok.Data;

@Data
public class PageReview {
	int searchCount;
	int totalNum;
	int currentPage;
	int lastPage;
	int searchStartNum;
	int product_id;
	boolean end;
	
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
