package com.goott.service;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawVO;

import java.util.List;
import java.util.Map;

public interface DrawService {
    /**
     * 이벤트 정보 가져오기
     *
     * @param draw_id 이벤트 번호
     * @return 이벤트 정보
     */
    public Map<String, Object> getDraw(int draw_id);

    /**
     * 모든 이벤트 정보 가져오기
     *
     * @return 이벤트 List
     */
    public List<Map<String, Object>> getAllDrawList();

    /**
     * 이벤트 중복 지원 확인
     *
     * @param drawEnterVO 이벤트 응모 엔티티
     * @return 0 정상, 나머지 중복
     */
    public int getCount(DrawEnterVO drawEnterVO);

    /**
     * 이벤트 응모 하기
     *
     * @param drawEnterVO
     */
    public void applicationDraw(DrawEnterVO drawEnterVO);

    /**
     * 응모자 확인
     *
     * @param draw_id
     * @return 응모자 List
     */
    public List<DrawEnterVO> getDrawEnterList(int draw_id);

    /**
     * 이벤트 중복 응모 여부 확인후 응모하기
     *
     * @param drawEnterVO
     * @return
     */
    public String registerDraw(DrawEnterVO drawEnterVO);

    /**
     * 이벤트 모집인원, 응모자수, 당첨자수 확인
     *
     * @param draw_id
     * @return Map
     */
    public Map<String, Object> getDrawEnterInfo(int draw_id);

    /**
     * 유저 이벤트 담첨 처리
     *
     * @param param (유저 아이디, 이벤트 번호)
     */
    public void userEnter(Map<String, Object> param);

    /**
     * 이벤트 수정
     *
     * @param drawVO
     */
    public void modifyDraw(DrawVO drawVO);

    /**
     * 신규 이벤트 등록
     *
     * @param drawVO
     */
    public void addDraw(DrawVO drawVO);

    /**
     * 이벤트 자동 마감 업데이트
     *
     */
    public void autoUpdateDrawEnd();
}

