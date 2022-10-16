package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.SessionVO;

public interface SessionService {
	/**
	 * 세션 정보 저장
	 * @param sessionVO
	 * @return
	 */
	public Integer setSessionInfo(SessionVO sessionVO);
	
	public Integer getCountTotal();
	public Integer getCountToday();
	public Integer getCountWeek();
	
	public Integer getCountBoardTotal();
	public Integer getCountBoardWeek();
	public Integer getCountBoardToday();
	
	public Integer getCountQnaTotal();
	public Integer getCountQnaWeek();
	public Integer getCountQnaToday();
	
	public Integer getCountSalesTotal();
	public Integer getCountSalesWeek();
	public Integer getCountSalesToday();
	
	/**
	 * 총 방문수, 이번주 방문수, 오늘 방문수
	 * @return
	 */
	public Map<String, Object> countBasic();
	
	public List<Map<String, Object>> countTotalDay();
	public List<Map<String, Object>> countTotalWeek();
	
	public List<Map<String, Object>> countTotalDayBoard();
	public List<Map<String, Object>> countTotalWeekBoard();
	
	public List<Map<String, Object>> countTotalDayQna();
	public List<Map<String, Object>> countTotalWeekQna();
	
	public List<Map<String, Object>> countTotalDayShop();
	public List<Map<String, Object>> countTotalWeekShop();
}
