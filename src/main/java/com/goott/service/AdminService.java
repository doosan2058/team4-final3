package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.MemberVO;

public interface AdminService {
	/**
	 * 회원 리스트
	 * @return
	 */
	public List<MemberVO> getMemberAllList();
	/**
	 * 회원 상세
	 * @param member_id
	 * @return
	 */
	public MemberVO getMemberInfo(String member_id);
	/**
	 * 자유게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getFreeList(String member_id);
	/**
	 * 캠핑게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getCampingList(String member_id);
	/**
	 * 질문게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getQnaList(String member_id);
	
	/**
	 * 구매 목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getOrderList(String member_id);
	/**
	 * 프로모션 동의한 이메일 리스트
	 * @return
	 */
	public List<String> getEmailList();
	
	public void sendPromotionEmail(List<String> toTemp, String subjectTemp, String textTemp);
	
	public void setPromotionEmail(String subject, String content);
}
