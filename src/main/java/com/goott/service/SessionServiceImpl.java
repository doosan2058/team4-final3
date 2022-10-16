package com.goott.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.SessionVO;
import com.goott.mapper.SessionMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class SessionServiceImpl implements SessionService {

	@Inject
	SessionMapper sessionMapper;
	
	@Override
	public Integer setSessionInfo(SessionVO sessionVO) {
		
		return sessionMapper.insertSession(sessionVO);
	}

	@Override
	public Integer getCountTotal() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountTotal();
	}

	@Override
	public Integer getCountToday() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountToday();
	}

	@Override
	public Integer getCountWeek() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountWeek();
	}

	@Override
	public Map<String, Object> countBasic() {
		int totalCount = this.getCountTotal() == null ? 0 : this.getCountTotal();
		int weekCount = this.getCountWeek() == null ? 0 : this.getCountWeek();
		int todayCount = this.getCountToday() == null ? 0 : this.getCountToday();
		
		int totalBoardCount = this.getCountBoardTotal() == null ? 0 : this.getCountBoardTotal();
		int weekBoardCount = this.getCountBoardWeek() == null ? 0 : this.getCountBoardWeek();
		int todayBoardCount = this.getCountBoardToday() == null ? 0 : this.getCountBoardToday();
		
		int totalQnaCount = this.getCountQnaTotal() == null ? 0 : this.getCountQnaTotal();
		int weekQnaCount = this.getCountQnaWeek() == null ? 0 : this.getCountQnaWeek();
		int todayQnaCount = this.getCountQnaToday() == null ? 0 : this.getCountQnaToday();
		
		int totalSalesCount = this.getCountSalesTotal() == null ? 0 : this.getCountSalesTotal();
		int weekSalesCount = this.getCountSalesWeek() == null ? 0 : this.getCountSalesWeek();
		int todaySalesCount = this.getCountSalesToday() == null ? 0 : this.getCountSalesToday();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("totalCount", totalCount);
		map.put("weekCount", weekCount);
		map.put("todayCount", todayCount);
		
		map.put("totalBoardCount", totalBoardCount);
		map.put("weekBoardCount", weekBoardCount);
		map.put("todayBoardCount", todayBoardCount);
		
		map.put("totalQnaCount", totalQnaCount);
		map.put("weekQnaCount", weekQnaCount);
		map.put("todayQnaCount", todayQnaCount);
		
		map.put("totalSalesCount", totalSalesCount);
		map.put("weekSalesCount", weekSalesCount);
		map.put("todaySalesCount", todaySalesCount);
		
		return map;
	}

	@Override
	public Integer getCountBoardTotal() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountBoardTotal();
	}

	@Override
	public Integer getCountBoardWeek() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountBoardWeek();
	}

	@Override
	public Integer getCountBoardToday() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountBoardToday();
	}

	@Override
	public Integer getCountQnaTotal() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountQnaTotal();
	}

	@Override
	public Integer getCountQnaWeek() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountQnaWeek();
	}

	@Override
	public Integer getCountQnaToday() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountQnaToday();
	}

	@Override
	public Integer getCountSalesTotal() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountSalesTotal();
	}

	@Override
	public Integer getCountSalesWeek() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountSalesWeek();
	}

	@Override
	public Integer getCountSalesToday() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountSalesToday();
	}

	@Override
	public List<Map<String, Object>> countTotalDay() {
		
		return sessionMapper.selectCountTotalDay();
	}

	@Override
	public List<Map<String, Object>> countTotalWeek() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountTotalWeek();
	}
	@Override
	public List<Map<String, Object>> countTotalDayBoard() {
		
		return sessionMapper.selectCountTotalDayBoard();
	}
	
	@Override
	public List<Map<String, Object>> countTotalWeekBoard() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountTotalWeekBoard();
	}
	@Override
	public List<Map<String, Object>> countTotalDayQna() {
		
		return sessionMapper.selectCountTotalDayQna();
	}
	
	@Override
	public List<Map<String, Object>> countTotalWeekQna() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountTotalWeekQna();
	}
	@Override
	public List<Map<String, Object>> countTotalDayShop() {
		
		return sessionMapper.selectCountTotalDayShop();
	}
	
	@Override
	public List<Map<String, Object>> countTotalWeekShop() {
		// TODO Auto-generated method stub
		return sessionMapper.selectCountTotalWeekShop();
	}

}
