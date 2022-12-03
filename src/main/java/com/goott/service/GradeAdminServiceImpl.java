package com.goott.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.GradeVO;
import com.goott.mapper.GradeMapper;

@Service
public class GradeAdminServiceImpl implements GradeAdminService{

	@Inject
	GradeMapper gradeMapper;
	@Inject
	S3Service s3Service;
	
	//관리자 - 등급 정책 리스트(gradePolicy_admin)
	@Override
	public List<GradeVO> gradePolicy() {
		return gradeMapper.gradePolicy();
	}
	
	// 등급 개수 가져오기
	@Override
	public int gradeCount() {
		return gradeMapper.gradeCount();
	}

	//관리자 - 등급 정책 삭제(gradePolicy_admin)
	@Override
	public String gradePolicyDelete(int grade_id) {
		int result = gradeMapper.gradePolicyDelete(grade_id);
		if(result==1) {
			return "삭제됐습니다.";
		}else {
			return "에러났음";
		}
	
	}

	@Override
	public void gradePolicyAdd(GradeVO gradeVO) {
		gradeMapper.gradePolicyAdd(gradeVO);
		
	}

	@Override
	public void gradePolicyModify(GradeVO gradeVO) {
		gradeMapper.gradePolicyModify(gradeVO);
		
	}




	
	
	
	
	

}
