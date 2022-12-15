package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.SessionVO;

public interface SessionService {
    /**
     * 세션 정보 저장
     *
     * @param sessionVO 세션 엔티티
     * @return
     */
    public Integer setSessionInfo(SessionVO sessionVO);

    /**
     * 전체 방문자수
     *
     * @return
     */
    public Integer getCountTotal();

    /**
     * 오늘 방문자수
     *
     * @return
     */
    public Integer getCountToday();

    /**
     * 7일간 방문자수
     *
     * @return
     */
    public Integer getCountWeek();

    /**
     * 오늘 자유게시판 작성글 수
     *
     * @return
     */
    public Integer getCountBoardTotal();

    /**
     * 7일간 자유게시판 작성글 수
     *
     * @return
     */
    public Integer getCountBoardWeek();

    /**
     * 오늘 자유게시판 작성글 수
     *
     * @return
     */
    public Integer getCountBoardToday();

    /**
     * 총 질문게시판 게시글 수
     *
     * @return
     */
    public Integer getCountQnaTotal();

    /**
     * 7일간 질문게시판 작성글 수
     *
     * @return
     */
    public Integer getCountQnaWeek();

    /**
     * 오늘 질문게시판 작성글 수
     *
     * @return
     */
    public Integer getCountQnaToday();

    /**
     * 총 매출 금액
     *
     * @return
     */
    public Integer getCountSalesTotal();

    /**
     * 7일간 매출 금액
     *
     * @return
     */
    public Integer getCountSalesWeek();

    /**
     * 오늘 매출 금액
     *
     * @return
     */
    public Integer getCountSalesToday();

    /**
     * (총 , 7일간 , 오늘) 방문수, 자유게시판 작성게시글 수, 질문게시판 작성게시글 수, 매출 금액
     *
     * @return
     */
    public Map<String, Object> countBasic();

    /**
     * 최근 7일간 일별 방문자 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalDay();

    /**
     * 최근 4주간 주별 방문자 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalWeek();

    /**
     * 최근 7일간 일별 자유게시판 작성글 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalDayBoard();

    /**
     * 최근 4주간 주별 자유게시판 작성글 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalWeekBoard();

    /**
     * 최근 7일간 일별 질문게시판 작성글 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalDayQna();

    /**
     * 최근 4주간 주별 자유게시판 작성글 수
     *
     * @return
     */
    public List<Map<String, Object>> countTotalWeekQna();

    /**
     * 최근 7일간 일별 매출 금액
     *
     * @return
     */
    public List<Map<String, Object>> countTotalDayShop();

    /**
     * 최근 4주간 주별 매출 금액
     *
     * @return
     */
    public List<Map<String, Object>> countTotalWeekShop();
}
