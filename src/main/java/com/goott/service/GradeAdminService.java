package com.goott.service;

import java.util.List;

import com.goott.domain.GradeVO;

public interface GradeAdminService {

	//관리자 - 등급 정책 리스트(gradePolicy_admin)
	public List<GradeVO> gradePolicy();
	
	//관리자 - 등급 총 개수
	public int gradeCount();
	
	//관리자 - 등급 정책 삭제
	public String gradePolicyDelete(int temp);
	
	public void gradePolicyAdd(GradeVO gradeVO);
	
	public void gradePolicyModify(GradeVO gradeVO);



}
