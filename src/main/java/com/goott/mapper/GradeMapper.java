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
     *
     * @param grade_id 등급 번호(PK)
     * @return 등급 엔티티
     */
    public GradeVO select(@Param("grade_id") int grade_id);


    /**
     * 회원의 등급 정보 가져오기
     *
     * @param member_id 회원 아이디(PK)
     * @return 회원 등급 정보 + 회원 아이디
     */
    public Map<String, Object> selectUserGradeId(String member_id);

    /**
     * 전체 등급 정보 가져오기
     *
     * @return 전체 등급 정보
     */
    public List<GradeVO> gradePolicyInfo();

    /**
     * 전체 등급 개수
     *
     * @return 전체 등급 개수
     */
    public int gradeCount();

    /**
     * 등급 삭제
     *
     * @param grade_id 등급 번호
     * @return
     */
    public int gradePolicyDelete(int grade_id);

    /**
     * 신규 등급 추가
     *
     * @param gradeVO
     */
    public void gradePolicyAdd(GradeVO gradeVO);


}
