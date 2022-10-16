package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.SessionVO;

@Mapper
public interface SessionMapper {
	/**
	 * 세션 생성 정보 저장
	 * @param sessionVO
	 * @return
	 */
	public Integer insertSession(SessionVO sessionVO);
	/**
	 * 총 방문자수
	 * @return
	 */
	public Integer selectCountTotal();
	/**
	 * 오늘 방문자수
	 * @return
	 */
	public Integer selectCountToday();
	/**
	 * 이번주 방문자수
	 * @return
	 */
	public Integer selectCountWeek();
	
	public Integer selectCountBoardTotal();
	public Integer selectCountBoardWeek();
	public Integer selectCountBoardToday();
	
	public Integer selectCountQnaTotal();
	public Integer selectCountQnaWeek();
	public Integer selectCountQnaToday();
	
	public Integer selectCountSalesTotal();
	public Integer selectCountSalesWeek();
	public Integer selectCountSalesToday();
	
	public List<Map<String, Object>> selectCountTotalDay();
	public List<Map<String, Object>> selectCountTotalWeek();
	
	public List<Map<String, Object>> selectCountTotalDayBoard();
	public List<Map<String, Object>> selectCountTotalWeekBoard();
	
	public List<Map<String, Object>> selectCountTotalDayQna();
	public List<Map<String, Object>> selectCountTotalWeekQna();
	
	public List<Map<String, Object>> selectCountTotalDayShop();
	public List<Map<String, Object>> selectCountTotalWeekShop();
}
