package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.MemberVO;

public interface AdminService {
    /**
     * 회원 리스트 가져오기
     *
     * @return
     */
    public List<MemberVO> getMemberAllList();

    /**
     * 회원 상세 정보 가져오기
     *
     * @param member_id
     * @return
     */
    public MemberVO getMemberInfo(String member_id);

    /**
     * 자유게시판 글목록 가져오기
     *
     * @param member_id
     * @return
     */
    public List<Map<String, Object>> getFreeList(String member_id);

    /**
     * 캠핑게시판 글목록 가져오기
     *
     * @param member_id
     * @return
     */
    public List<Map<String, Object>> getCampingList(String member_id);

    /**
     * 질문게시판 글목록 가져오기
     *
     * @param member_id
     * @return
     */
    public List<Map<String, Object>> getQnaList(String member_id);

    /**
     * 구매 목록 가져오기
     *
     * @param member_id
     * @return
     */
    public List<Map<String, Object>> getOrderList(String member_id);

    /**
     * 프로모션 동의한 이메일 리스트 가져오기
     *
     * @return
     */
    public List<String> getEmailList();

    /**
     * 다수에게 이메일 보내기
     *
     * @param toTemp      이메일 수신 list
     * @param subjectTemp 제목
     * @param textTemp    내용
     */
    public void sendPromotionEmail(List<String> toTemp, String subjectTemp, String textTemp);

	/**
	 * 다수에게 이메일 보내기 세팅(이메일 수신동의 회원 목록 + 이메일 내용)
	 *
	 * @param subject 제목
	 * @param content 내용
	 */
    public void setPromotionEmail(String subject, String content);

    /**
     * 공지사항 상위 5개
     *
     * @return
     */
    public List<Map<String, Object>> getNoticeList();
}
