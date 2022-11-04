package com.goott.mapper;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface DrawMapper {
    /**
     * 이벤트 정보 가져오기
     * @param draw_id
     * @return 이벤트 정보
     */
    public Map<String, Object> select(int draw_id);
    /**
     * 모든 이벤트 정보 가져오기
     * @return 이벤트 정보 List
     */
    public List<Map<String, Object>> selectList();

    /**
     * 이벤트 중복 지원 확인
     * @param drawEnterVO
     * @return 0 정상, 나머지 중복
     */
    public int selectCount(DrawEnterVO drawEnterVO);

    /**
     * 이벤트 응모
     * @param drawEnterVO
     */
    public void insertDrawEnter(DrawEnterVO drawEnterVO);

    /**
     * 응모자 확인
     * @param draw_id
     * @return 응모자 List
     */
    public List<DrawEnterVO> selectDrawEnterList(int draw_id);

    /**
     * 이벤트 모집인원, 응모자수, 당첨자수 확인
     * @param draw_id
     * @return Map
     */
    public Map<String, Object> selectDrawEnterInfo(int draw_id);

    /**
     * 유저 이벤트 당첨 처리
     * @param param (유저 아이디, 이벤트 번호)
     */
    public void updateDrawEnter(Map<String, Object> param);

    /**
     * 이벤트 수정
     * @param drawVO
     */
    public void updateDraw(DrawVO drawVO);

}
