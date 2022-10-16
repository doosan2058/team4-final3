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
	
	//등급 정보 가져오기
	@Override
	public GradeVO select(int grade_id) {
		
		return gradeMapper.select(grade_id);
	}

	//회원 등급 정보 + 회원 아이디 가져오기
	@Override
	public Map<String, Object> getUserGradeInfo(String member_id) {
	
		return gradeMapper.selectUserGradeId(member_id);
	}

	//등급 정보 전체 가져오기
	@Override
	public List<GradeVO> gradePolicyInfo() {
	
		return gradeMapper.gradePolicyInfo();
	}

}
