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

        return sessionMapper.selectCountTotal();
    }

    @Override
    public Integer getCountToday() {

        return sessionMapper.selectCountToday();
    }

    @Override
    public Integer getCountWeek() {

        return sessionMapper.selectCountWeek();
    }

    @Override
    public Map<String, Object> countBasic() {
        // count 값이 null 일때 0 으로 초기화
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

        Map<String, Object> map = new HashMap<>(); // 각 일, 주, 전체 방문자 수, 자유게시판 작성글 수, 질문 게시판 작성글 수, 매출 금액

        map.put("totalCount", totalCount); // 전체, 주, 일 방문자 수
        map.put("weekCount", weekCount);
        map.put("todayCount", todayCount);

        map.put("totalBoardCount", totalBoardCount); // 전체, 주, 일 자유게시판 게시글 작성 수
        map.put("weekBoardCount", weekBoardCount);
        map.put("todayBoardCount", todayBoardCount);

        map.put("totalQnaCount", totalQnaCount); // 전체, 주, 일 질문게시판 게시글 작성 수
        map.put("weekQnaCount", weekQnaCount);
        map.put("todayQnaCount", todayQnaCount);

        map.put("totalSalesCount", totalSalesCount); // 전체, 주, 일 매출 금액
        map.put("weekSalesCount", weekSalesCount);
        map.put("todaySalesCount", todaySalesCount);

        return map;
    }

    @Override
    public Integer getCountBoardTotal() {

        return sessionMapper.selectCountBoardTotal();
    }

    @Override
    public Integer getCountBoardWeek() {

        return sessionMapper.selectCountBoardWeek();
    }

    @Override
    public Integer getCountBoardToday() {

        return sessionMapper.selectCountBoardToday();
    }

    @Override
    public Integer getCountQnaTotal() {

        return sessionMapper.selectCountQnaTotal();
    }

    @Override
    public Integer getCountQnaWeek() {

        return sessionMapper.selectCountQnaWeek();
    }

    @Override
    public Integer getCountQnaToday() {

        return sessionMapper.selectCountQnaToday();
    }

    @Override
    public Integer getCountSalesTotal() {

        return sessionMapper.selectCountSalesTotal();
    }

    @Override
    public Integer getCountSalesWeek() {

        return sessionMapper.selectCountSalesWeek();
    }

    @Override
    public Integer getCountSalesToday() {

        return sessionMapper.selectCountSalesToday();
    }

    @Override
    public List<Map<String, Object>> countTotalDay() {

        return sessionMapper.selectCountTotalDay();
    }

    @Override
    public List<Map<String, Object>> countTotalWeek() {

        return sessionMapper.selectCountTotalWeek();
    }

    @Override
    public List<Map<String, Object>> countTotalDayBoard() {

        return sessionMapper.selectCountTotalDayBoard();
    }

    @Override
    public List<Map<String, Object>> countTotalWeekBoard() {

        return sessionMapper.selectCountTotalWeekBoard();
    }

    @Override
    public List<Map<String, Object>> countTotalDayQna() {

        return sessionMapper.selectCountTotalDayQna();
    }

    @Override
    public List<Map<String, Object>> countTotalWeekQna() {

        return sessionMapper.selectCountTotalWeekQna();
    }

    @Override
    public List<Map<String, Object>> countTotalDayShop() {

        return sessionMapper.selectCountTotalDayShop();
    }

    @Override
    public List<Map<String, Object>> countTotalWeekShop() {

        return sessionMapper.selectCountTotalWeekShop();
    }

}
