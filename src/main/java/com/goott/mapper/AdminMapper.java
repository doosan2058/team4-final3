package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.MemberVO;

@Mapper
public interface AdminMapper {
    /**
     * 회원 전체 리스트
     *
     * @return 회원 전체 리스트
     */
    public List<MemberVO> selectAllMemberList();

    /**
     * 회원 상세 정보
     *
     * @param member_id 아이디
     * @return 회원 상세 정보
     */
    public MemberVO selectMemberInfo(String member_id);

    /**
     * 자유게시판 글목록
     *
     * @param member_id 아이디
     * @return 자유게시판 글목록
     */
    public List<Map<String, Object>> selectFreeList(String member_id);

    /**
     * 캠핑게시판 글목록
     *
     * @param member_id 아이디
     * @return 캠핑게시판 글목록
     */
    public List<Map<String, Object>> selectCampingList(String member_id);

    /**
     * 질문게시판 글목록
     *
     * @param member_id 아이디
     * @return 질문게시판 글목록
     */
    public List<Map<String, Object>> selectQnaList(String member_id);

    /**
     * 구매 목록
     *
     * @param member_id 아이디
     * @return 구매 목록
     */
    public List<Map<String, Object>> selectOrderList(String member_id);

    /**
     * 이메일 수신 동의 한 회원들의 이메일 목록
     *
     * @return 이메일 목록
     */
    public List<String> selectEmailList();

    /**
     * 공지사항 최신순 5개
     *
     * @return 공지사항 목록
     */
    public List<Map<String, Object>> selectNoticeList();


}
