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
        this.clientPageNum = clientPageNum; // 클라이언트가 요청한 페이지 번호
        this.startRownum = (clientPageNum - 1) * 10; // 검색 시작할 게시글 번호
        this.rownumEndCount = 10; // 10개씩 검색
        this.totalCount = totalCount; // 전체 게시글 개수
        this.totalPage =(int) Math.ceil(totalCount / (rownumEndCount * 1.0)); // 전체 페이지 개수

        this.blockCount = 10; // 한 블록에 나타낼 페이지 개수
        this.totalBlock =(int) Math.ceil(this.totalPage / (this.blockCount * 1.0)); // 총 블록 개수
        this.currentBlockNum = (int) Math.ceil(this.clientPageNum / (this.blockCount * 1.0)); // 현재 블록 번호

        this.endPageBlock = (this.clientPageNum == totalPage) ? false : true; // 끝 블록으로 가는 버튼
        this.startPageBlock = (this.clientPageNum == 1) ? false : true; // 첫 블록으로 가는 버튼
        this.nextPage = (this.clientPageNum == totalPage) ? false : true; // 다음 페이지 버튼
        this.prePage = (this.clientPageNum == 1) ? false : true; // 이전 페이지 버튼

        this.qna_category = qna_category; // 검색 카테고리
        this.qnaSearchText = qnaSearchText; // 검색어
    }
}
