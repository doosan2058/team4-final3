package com.goott.domain;

public class PageQna {
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
    int blockNum;

    boolean nextPage;
    boolean endPage;
    boolean prePage;
    boolean startPage;

    // 카테고리
    String qna_category;

    // 검색어
    String qnaSearchText;
}
