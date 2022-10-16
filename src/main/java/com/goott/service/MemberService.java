package com.goott.service;

import java.util.Date;
import java.util.Map;

import com.goott.domain.MemberVO;

public interface MemberService {
	
	
	/**
	 * 회원 등급 정보 가져오기
	 * @param member_id 아이디
	 * @return 회원 등급이름, 할인율, 적립율
	 */
	public Map<String, Object> getUserGradeInfo(String member_id);
	
	/**
	 * 회원 주소 가져오기
	 * @param member_id 아이디
	 * @return 우편번호, 주소
	 */
	public Map<String, Object> getUserAddress(String member_id);
	
	/**
	 * 회원 가입
	 * @param memberVO 회원 엔티티
	 */
	public void joinMember(MemberVO memberVO);
	
	
	/**
	 * 비번 가져오기
	 * @param member_id 유저 아이디
	 * @return 			암호화된 유저 비밀번호
	 */
	public String getPassword(String member_id);
	
	/**
	 * 로그인시 비번 체크
	 * @param member_id
	 * @param member_pw
	 * @return 해당 아이디의 비밀번호와 입력된 비밀번호 일치 >> return true
	 *         불일치 >> return false
	 */
	public boolean checkPw(String member_id, String member_pw);
	
	
	/**
	 * 아이디 중복 확인
	 * @param member_id 아이디
	 * @return 검색된 아이디 개수
	 */
	public int checkDupId(String member_id);
	
	
	/**
	 * 이메일 중복 확인
	 * @param member_email 이메일
	 * @return 검색된 이메일 개수
	 */
	public int checkDupEmail(String member_email);
	
	/**
	 * 로그인 체크 메서드
	 * checkDupId() , checkPw() 이용
	 * @param member_id 아이디
	 * @param member_pw 비밀번호
	 * @return 1.중복된 아이디가 없다 >> 아이디가 존재하지 않는다 >> return 0 >> '아이디가 존재하지 않습니다' 리턴
	 *  	   2.중복된 아이디가 존재한다 >> 아이디가 존재한다 >> return 1 >> 비밀번호 체크 진행
	 *         3.비밀번호가 일치하지 않는다 >> return false
	 *         4.비밀번호가 일치한다 >> return true >> 로그인 처리
	 */
	public String loginCheck(String member_id, String member_pw);
	
	/**
	 * 유저 로그인 시도 횟수 가져오기
	 * @param member_id 아이디
	 * @return 로그인 시도 횟수
	 */
	public int getUserLoginCount(String member_id);
	
	/**
	 * 유저 로그인 시도 횟수 + 1
	 * @param member_id 아이디
	 */
	public void addUserLoginCount(String member_id);
	
	
	
	/**
	 * 유저 로그인 시도 횟수 초기화
	 * @param member_id 아이디
	 */
	public void initUserLoginCount(String member_id);
	
	/**
	 * 유저 로그인 제한일 설정 
	 * @param member_id
	 */
	public void setOffLimitsUserLogin(String member_id);
	
	
	/**
	 * 유저 로그인 제한일 초기화
	 * @param member_id
	 */
	public void initOffLimitsUserLogin(String member_id);
	
	
	/**
	 * 유저 로그인 제한일 가져오기
	 * @param member_id 아이디
	 * @return 제한일
	 */
	public Date getUserOffLimitsDate(String member_id);
	
	/**
	 * 디비 기준 현재 시간 가져오기
	 * @return 현재 시간
	 */
	public Date getTime();
	
	
	/**
	 * 로그인 성공시 마지막 접속 시간 업데이트 메서드
	 * @param member_id 멤버 아이디
	 */
	public void setLastLoginDate(String member_id);
	
	/**
	 * 로그인 유저의 권한 가져오는 메서드
	 * @param member_id 아이디
	 * @return 권한(회원/관리자)
	 */
	public String getUserAuth(String member_id);
	
	/**
	 * 탈퇴 회원인지 확인
	 * @param member_id 아이디
	 * @return ( y/n )
	 */
	public String checkWithDrawal(String member_id);
	
	/**
	 * 탈퇴 처리 메서드
	 * @param member_id 아이디
	 * @return ( 1/0 ) 1: 성공 , 0: 실패
	 */
	public int doWithDrawal(String member_id);
	
	/**
	 * 회원 정보 가져오기
	 * @param member_id 아이디
	 * @return 회원 엔티티
	 */
	public MemberVO getMemberInfo(String member_id);
	/**
	 * 비밀번호 암호화
	 * @param password
	 * @return 암호화된 비밀번호
	 */
	public String pwEncode(String password);
	/**
	 * 로그인시 비밀번호 초기화 상태인지 확인
	 * @param member_id 아이디
	 * @return y/n
	 */
	public String getInitPw(String member_id);
}
