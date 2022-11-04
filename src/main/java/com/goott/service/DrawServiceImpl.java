package com.goott.service;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawVO;
import com.goott.mapper.DrawMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class DrawServiceImpl implements DrawService {

    @Autowired
    DrawMapper drawMapper;

    @Override
    public Map<String, Object> getDraw(int draw_id) {
        return drawMapper.select(draw_id);
    }

    @Override
    public List<Map<String, Object>> getAllDrawList() {
        return drawMapper.selectList();
    }

    @Override
    public int getCount(DrawEnterVO drawEnterVO) {
        return drawMapper.selectCount(drawEnterVO);
    }

    @Override
    public void applicationDraw(DrawEnterVO drawEnterVO) {
        drawMapper.insertDrawEnter(drawEnterVO);
    }

    @Override
    public List<DrawEnterVO> getDrawEnterList(int draw_id) {
        return drawMapper.selectDrawEnterList(draw_id);
    }

    @Override
    public String registerDraw(DrawEnterVO drawEnterVO) {
        //이벤트 중복 등록 확인
        int resultCount = this.getCount(drawEnterVO);

        if(resultCount != 0){
            return "이미 응모하였습니다.";
        }
        else{
            this.applicationDraw(drawEnterVO);
            return "이벤트에 응모 하였습니다.";
        }
    }

    @Override
    public Map<String, Object> getDrawEnterInfo(int draw_id) {
        return drawMapper.selectDrawEnterInfo(draw_id);
    }

    @Override
    public void userEnter(Map<String, Object> param) {
        drawMapper.updateDrawEnter(param);
    }

    @Override
    public void modifyDraw(DrawVO drawVO) {
        drawMapper.updateDraw(drawVO);
    }


}
