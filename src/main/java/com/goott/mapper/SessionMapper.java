package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.SessionVO;

@Mapper
public interface SessionMapper {
    /**
     * 세션 생성 정보 저장(접속 정보)
     *
     * @param sessionVO 접속 브라우저, 접속 아이피
     * @return 처리 결과
     */
    public Integer insertSession(SessionVO sessionVO);

    /**
     * 총 방문자수
     *
     * @return
     */
    public Integer selectCountTotal();

    /**
     * 오늘 방문자수
     *
     * @return
     */
    public Integer selectCountToday();

    /**
     * 이번주 방문자수
     *
     * @return
     */
    public Integer selectCountWeek();

    /**
     * 총 자유게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountBoardTotal();

    /**
     * 이번주 작성된 자유게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountBoardWeek();

    /**
     * 오늘 작성된 자유게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountBoardToday();

    /**
     * 총 질문 게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountQnaTotal();

    /**
     * 이번주 작성된 질문게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountQnaWeek();

    /**
     * 오늘 작성된 질문게시판 게시글 수
     *
     * @return
     */
    public Integer selectCountQnaToday();

    /**
     * 전체 매출액
     *
     * @return
     */
    public Integer selectCountSalesTotal();

    /**
     * 이번주 매출액
     *
     * @return
     */
    public Integer selectCountSalesWeek();

    /**
     * 오늘 매출액
     *
     * @return
     */
    public Integer selectCountSalesToday();

    /**
     * 최근 7일 일별 접속자 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalDay();

    /**
     * 최근 4주 주간 접속자 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalWeek();

    /**
     * 최근 7일 일별 작성된 자유게시판 게시글 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalDayBoard();

    /**
     * 최근 4주 주간 작성된 자유게시판 게시글 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalWeekBoard();

    /**
     * 최근 7일 일별 작성된 질문게시판 게시글 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalDayQna();

    /**
     * 최근 4주 주간 작성된 질문게시판 게시글 수
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalWeekQna();

    /**
     * 최근 7일 일별 매출
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalDayShop();

    /**
     * 최근 4주 주별 매출
     *
     * @return
     */
    public List<Map<String, Object>> selectCountTotalWeekShop();
}
