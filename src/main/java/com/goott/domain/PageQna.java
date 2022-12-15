package com.goott.domain;

import lombok.Data;

@Data
public class PageQna {
    // 사용자 호출 페이지 번호
    private int clientPageNum;

    // page rownum 시작 번호
    private int startRownum;

    // page rownum 개수 (10개)
    private int rownumEndCount;

    // 총 질문글 개수
    private int totalCount;

    // 총 페이지 수
    private int totalPage;

    // 한 블럭에 페이지 개수 (10개)
    private int blockCount;

    // 총 블럭 개수
    private int totalBlock;

    // 현재 블럭 번호
    private int currentBlockNum;

    private boolean nextPage;
    private boolean endPageBlock;
    private boolean prePage;
    private boolean startPageBlock;

    // 카테고리
    private String qna_category;

    // 검색어
    private String qnaSearchText;

    // 관리자용 답변 여부
    private String qna_admin_answer;

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
