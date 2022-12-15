package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.GradeVO;

public interface GradeService {

    /**
     * 등급 정보 가져오기
     *
     * @param grade_id 등급 번호(PK)
     * @return 등급 엔티티
     */
    public GradeVO select(int grade_id);

    /**
     * 회원의 등급 정보 가져오기
     *
     * @param member_id 회원 아이디
     * @return 등급 엔티티 + 회원 아이디
     */
    public Map<String, Object> getUserGradeInfo(String member_id);

    /**
     * 등급 전체 정보 가져오기
     *
     * @return
     */
    public List<GradeVO> gradePolicyInfo();

    /**
     * 전체 등급 개수 가져오기
     *
     * @return
     */
    public int gradeCount();

    /**
     * 등급 삭제하기
     *
     * @param grade_id 등급 번호
     * @return 결과 text
     */
    public String gradePolicyDelete(int grade_id);

    /**
     * 신규 등급 추가
     *
     * @param gradeVO 등급 정보 엔티티
     */
    public void gradePolicyAdd(GradeVO gradeVO);
}
