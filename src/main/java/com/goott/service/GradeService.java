package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.GradeVO;

public interface GradeService {

	/**
	 * 등급 정보 가져오기
	 * @param grade_id 등급 번호(PK)
	 * @return 등급 엔티티
	 */
	public GradeVO select(int grade_id);
	
	/**
	 * 회원의 등급 정보 가져오기
	 * @param member_id 회원 아이디
	 * @return 등급 엔티티 + 회원 아이디
	 */
	public Map<String, Object> getUserGradeInfo(String member_id);

	/**
	 * 등급 전체 정보 가져오기
	 * @return
	 */
	public List<GradeVO> gradePolicyInfo();
}
