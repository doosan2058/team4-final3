package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goott.domain.GradeVO;

@Mapper
public interface GradeMapper {
	
	/**
	 * 등급 정보 가져오기
	 * @param grade_id 등급 번호(PK)
	 * @return 등급 엔티티
	 */
	public GradeVO select(@Param("grade_id") int grade_id); 
	
	
	/**
	 * 회원의 등급 정보 가져오기
	 * @param member_id 회원 아이디(PK)
	 * @return 회원 등급 정보 + 회원 아이디
	 */
	public Map<String, Object> selectUserGradeId(String member_id);
	
	/* 등급 정보 전체 가져오기 */
	public List<GradeVO> gradePolicyInfo();
	

	/* 관리자 - 등급 정책 리스트 */
	public List<GradeVO> gradePolicy();
	
	/* 관리자 - 등급 개수 */
	public int gradeCount();
	
	/* 관리자 - 등급 정책 삭제 */
	public int gradePolicyDelete(int grade_id);
	
	/* 관리자 - 등급 정책 추가 */
	public void gradePolicyAdd(GradeVO gradeVO);
	
	/* 관리자 - 등급 정책 수정 */
	public void gradePolicyModify(GradeVO gradeVO);
	
}
