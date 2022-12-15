package com.goott.mapper;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goott.domain.MemberVO;

@Mapper
public interface MemberMapper {

    /**
     * 회원 등급 정보 가져오기
     *
     * @param member_id 아이디
     * @return 회원 등급이름, 할인율, 적립율
     */
    public Map<String, Object> selectGradeInfo(String member_id);

    /**
     * 회원 주소 가져오기
     *
     * @param member_id 아이디
     * @return 우편번호, 주소
     */
    public Map<String, Object> selectAddress(String member_id);

    /**
     * 회원 가입
     *
     * @param memberVO 멤버 엔티티(암호화된 비밀번호)
     */
    public void insertMember(MemberVO memberVO);

    /**
     * 비밀번호 가져오기
     *
     * @param member_id 유저 아이디
     * @return 유저 암호화된 패스워드
     */
    public String selectPw(@Param("member_id") String member_id);

    /**
     * 아이디 중복 확인
     * 0이 아니면 중복 아이디
     *
     * @param member_id 입력 아이디
     * @return 일치하는 아이디 수
     */
    public int countId(@Param("member_id") String member_id);

    /**
     * 이메일 중복 확인
     * 0이 아니면 중복 이메일
     *
     * @param member_email 입력 이메일
     * @return 일치하는 이메일 수
     */
    public int countEmail(@Param("member_email") String member_email);


    /**
     * 유저 로그인 시도 횟수 가져오기
     *
     * @param member_id 아이디
     * @return 로그인 시도 횟수
     */
    public int selectLoginCount(String member_id);

    /**
     * 유저 로그인 시도 횟수 + 1 업데이트
     *
     * @param member_id 아이디
     */
    public void updateLoginCount(String member_id);


    /**
     * 유저 로그인 시도 횟수 초기화
     *
     * @param member_id 아이디
     */
    public void initLoginCount(String member_id);


    /**
     * 유저 로그인 제한일 내일로 설정
     *
     * @param member_id 아이디
     */
    public void updateOffLimitsDate(String member_id);

    /**
     * 유저 로그인 제한일 초기화
     *
     * @param member_id 아이디
     */
    public void initOffLimitsDate(String member_id);


    /**
     * 유저 로그인 제한날짜 가져오기
     *
     * @param member_id 아이디
     * @return 로그인 제한일
     */
    public Date selectOffLimitsDate(String member_id);


    /**
     * 로그인 성공시 최근 로그인 시간 업데이트
     *
     * @param member_id 아이디
     */
    public void updateLoginDate(String member_id);

    /**
     * 로그인 유저의 권한 가져오기
     *
     * @param member_id 아이디
     * @return 권한
     */
    public String selectAuth(String member_id);

    /**
     * 로그인 시도하는 유저의 탈퇴 여부 확인
     *
     * @param member_id 아이디
     * @return (y / n)
     */
    public String selectWithdrawal(String member_id);

    /**
     * 회원 탈퇴 처리
     *
     * @param member_id 아이디
     * @return (1 / 0) 1: 성공 0: 실패
     */
    public int updateWithdrawal(String member_id);

    /**
     * 회원 정보 가저오기
     *
     * @param member_id 아이디
     * @return
     */
    public MemberVO selectMemberInfo(String member_id);

    /**
     * 회원 이메일 정보 가져오기
     *
     * @param member_id
     * @return member_email
     */
    public String selectEmail(String member_id);

    /**
     * 비밀번호 초기화 상태인지 확인
     *
     * @param member_id
     * @return y/n
     */
    public String selectInitPw(String member_id);

    /**
     * 비밀번호 초기화 신청한 상태로 업데이트
     *
     * @param member_email
     */
    public void updateInitPw(String member_email);

    /**
     * 비밀번호 초기화 완료 상태로 업데이트
     *
     * @param member_id
     */
    public void updateCompletePw(String member_id);

}
