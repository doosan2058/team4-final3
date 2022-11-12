package com.goott.domain;

import lombok.Data;

@Data
public class PageQna {
    // 사용자 호출 페이지 번호
    int clientPageNum;

    // page rownum 시작 번호
    int startRownum;

    // page rownum 개수 (10개)
    int rownumEndCount;

    // 총 질문글 개수
    int totalCount;

    // 총 페이지 수
    int totalPage;

    // 한 블럭에 페이지 개수 (10개)
    int blockCount;

    // 총 블럭 개수
    int totalBlock;

    // 현재 블럭 번호
    int currentBlockNum;

    boolean nextPage;
    boolean endPageBlock;
    boolean prePage;
    boolean startPageBlock;

    // 카테고리
    String qna_category;

    // 검색어
    String qnaSearchText;

    public PageQna(int clientPageNum, int totalCount, String qna_category, String qnaSearchText){
        this.clientPageNum = clientPageNum;
        this.startRownum = (clientPageNum - 1) * 10;
        this.rownumEndCount = 10;
        this.totalCount = totalCount;
        this.totalPage =(int) Math.ceil(totalCount / (rownumEndCount * 1.0));

        this.blockCount = 10;
        this.totalBlock =(int) Math.ceil(this.totalPage / (this.blockCount * 1.0));
        this.currentBlockNum = (int) Math.ceil(this.clientPageNum / (this.blockCount * 1.0));

        this.endPageBlock = (this.clientPageNum == totalPage) ? false : true;
        this.startPageBlock = (this.clientPageNum == 1) ? false : true;
        this.nextPage = (this.clientPageNum == totalPage) ? false : true;
        this.prePage = (this.clientPageNum == 1) ? false : true;

        this.qna_category = qna_category;
        this.qnaSearchText = qnaSearchText;
    }
}
