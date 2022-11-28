package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.MemberVO;

@Mapper
public interface AdminMapper {
	/**
	 * 회원 리스트
	 * @return
	 */
	public List<MemberVO> selectAllMemberList();
	/**
	 * 회원 상세
	 * @param member_id 아이디
	 * @return
	 */
	public MemberVO selectMemberInfo(String member_id);
	/**
	 * 자유게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> selectFreeList(String member_id);
	/**
	 * 캠핑게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> selectCampingList(String member_id);
	/**
	 * 질문게시판 글목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> selectQnaList(String member_id);
	/**
	 * 구매 목록
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> selectOrderList(String member_id);
	
	/**
	 * 이메일 수신 동의한 이메일 목록
	 * @return
	 */
	public List<String> selectEmailList();

	/**
	 * 공지사항 상위 5개
	 * @return
	 */
	public List<Map<String, Object>> selectNoticeList();
	
	
}
