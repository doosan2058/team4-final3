package com.goott.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.GradeVO;
import com.goott.mapper.GradeMapper;

@Service
public class GradeServiceImpl implements GradeService {

    @Inject
    GradeMapper gradeMapper;


    @Override
    public GradeVO select(int grade_id) {

        return gradeMapper.select(grade_id);
    }


    @Override
    public Map<String, Object> getUserGradeInfo(String member_id) {

        return gradeMapper.selectUserGradeId(member_id);
    }


    @Override
    public List<GradeVO> gradePolicyInfo() {

        return gradeMapper.gradePolicyInfo();
    }

    @Override
    public int gradeCount() {
        return gradeMapper.gradeCount();
    }

    @Override
    public String gradePolicyDelete(int grade_id) {
        int result = gradeMapper.gradePolicyDelete(grade_id);

        if (result == 1) {
            return "삭제하였습니다.";
        } else {
            return "삭제 도중 오류가 발생하였습니다.";
        }
    }

    @Override
    public void gradePolicyAdd(GradeVO gradeVO) {
        gradeMapper.gradePolicyAdd(gradeVO);
    }

}
