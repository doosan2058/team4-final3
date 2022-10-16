package com.goott.service;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goott.domain.MemberVO;
import com.goott.mapper.MemberMapper;
import com.goott.mapper.TimeMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {

//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	MemberMapper memberMapper;

	@Inject
	TimeMapper timeMapper;

	/**
	 * 회원가입 메서드
	 * 
	 * @param 멤버 엔티티 1.멤버 비밀번호 암호화 2.암호화된 비밀번호 멤버 엔티티 다시 저장 3.디비저장
	 */
	@Override
	public void joinMember(MemberVO memberVO) {
		// 비밀번호 암호화
		String securePw = this.pwEncode(memberVO.getMember_pw());
		// 암호화한 비밀번호 vo에 저장
		memberVO.setMember_pw(securePw);
		// DAO로 vo 전송
		memberMapper.insertMember(memberVO);

	}

	/**
	 * 패스워드 암호화 매서드
	 * 
	 * @param password 유저 입력 패스워드
	 * @return 암호화된 패스워드
	 */
	public String pwEncode(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		
		return encodedPassword;

	}

	/**
	 * 비번 체크
	 * 
	 * @param member_id
	 * @param member_pw
	 * @return 해당 아이디의 비밀번호와 입력된 비밀번호 일치 >> return true 불일치 >> return false
	 */
	@Override
	public boolean checkPw(String member_id, String member_pw) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = this.getPassword(member_id);
		return passwordEncoder.matches(member_pw, encodedPassword);

	}

	@Override
	public String getPassword(String member_id) {

		return memberMapper.selectPw(member_id);
	}

	/**
	 * 아이디 중복 확인
	 * 
	 * @param member_id 아이디
	 * @return 검색된 아이디 개수
	 */
	@Override
	public int checkDupId(String member_id) {

		return memberMapper.countId(member_id);

	}

	/**
	 * 이메일 중복 확인
	 * 
	 * @param member_email 이메일
	 * @return 검색된 이메일 개수
	 */
	@Override
	public int checkDupEmail(String member_email) {

		return memberMapper.countEmail(member_email);
	}

	/**
	 * 로그인 체크 메서드 checkDupId() , checkPw() 이용
	 * 
	 * @param member_id 아이디
	 * @param member_pw 비밀번호
	 * @return 1.중복된 아이디가 없다 >> 아이디가 존재하지 않는다 >> return 0 >> '아이디가 존재하지 않습니다' 리턴
	 *         2.중복된 아이디가 존재한다 >> 아이디가 존재한다 >> return 1 >> 비밀번호 체크 진행 3.비밀번호가 일치하지
	 *         않는다 >> return false 4.비밀번호가 일치한다 >> return true >> 로그인 처리
	 */
	@Transactional
	@Override
	public String loginCheck(String member_id, String member_pw) {
		// 로그인 제한 기한에 걸리지 않다면 or 로그인 제한 기한이 null 이면
		// 아이디 검색
		int dupIdCountTemp = this.checkDupId(member_id);

		if (dupIdCountTemp == 0) {
			return "존재하지 않는 아이디 입니다.";
		}

		// 탈퇴 처리한 멤버인지 확인하는 로직 작성해야함
		String tempWidhDrawal = this.checkWithDrawal(member_id);
		// 탈퇴 처리한 유저면 리턴
		if (tempWidhDrawal.equals("y"))
			return "탈퇴한 회원 입니다.";

		// 만약 로그인 제한 기한에 걸리면
		Date date = this.getUserOffLimitsDate(member_id);
		Date now = this.getTime();
		// 로그인 제한이 없다면 (null 이라면 ) 다음 진행
		// 로그인 제한이 있다면 현재 시간과 비교
		// 현재 시간이 더 크다면 로그인 제한 횟수 초기화 후 진행
		// 로그인 제한일이 더 크다면 로그인 금지
		if (date != null) {
			/**
			 * 음수 : now 가 작음 0 : 같음 양수 : now 가 큼
			 */
			int dateCount = now.compareTo(date);
			// 아직 제한일 안지났을시
			if (dateCount <= 0) {
				return "죄송합니다. 현재 해당 아이디는 로그인 제한 상태 입니다.";
			}
			// 제한일 이후
			else if (dateCount > 0) {
//				log.info("로그인 제한 일자가 지나 [ " + member_id + " ] 의 로그인 제한을 초기화 합니다.");
				// 로그인 시도 횟수 초기화
				this.initUserLoginCount(member_id);
			}
			// 이외는 오류
			else
				return "심각한 오류 발생.";
		}

		// 로그인 제한 기한에 걸리지 않다면 or 로그인 제한 기한이 null 이면
		// 아이디 검색
		int dupIdCount = this.checkDupId(member_id);

		if (dupIdCount == 0) {
			return "존재하지 않는 아이디 입니다.";
		} else if (dupIdCount == 1) {
			// 비밀번호 비교
			boolean pwCheckFlag = this.checkPw(member_id, member_pw);
			// 아이디 존재, 비밀번호 일치
			if (pwCheckFlag) {
				// 로그인 시도 횟수 초기화
				this.initUserLoginCount(member_id);
				// 로그인 제한 일 초기화
				this.initOffLimitsUserLogin(member_id);
				// 마지막 로그인 날짜 업데이트
				this.setLastLoginDate(member_id);
				return "로그인 성공.";
			}
			// 아이디 존재, 비밀번호 불일치
			else {
				// 로그인 시도 횟수 증가
				this.addUserLoginCount(member_id);
				int tempCount = this.getUserLoginCount(member_id);
				// 만약 누적 5회 이상 시도하였다면
				if (tempCount == 5) {
					// 로그인 제한 날짜 설정
					this.setOffLimitsUserLogin(member_id);
					return "비밀번호 5회 불일치 하여 로그인을 일시 금지 합니다.";
				}
				// 아직 5회 미만이라면
				else
					return "비밀번호가 일치하지 않습니다. 비밀번호 불일치 횟수 : (" + tempCount + ")회.";
			}
		} else
			return "심각한 오류 발생";

	}

	/**
	 * 유저 로그인 횟수 가져오는 메서드
	 */
	@Override
	public int getUserLoginCount(String member_id) {

		return memberMapper.selectLoginCount(member_id);
	}

	/**
	 * 유저 로그인 횟수 +1 증가 하는 메서드
	 */
	@Override
	public void addUserLoginCount(String member_id) {
		memberMapper.updateLoginCount(member_id);

	}

	/**
	 * 유저 로그인 시도 횟수 초기화 시키는 메서드
	 */
	@Override
	public void initUserLoginCount(String member_id) {
		memberMapper.initLoginCount(member_id);

	}

	/**
	 * 유저 로그인 제한일 + 1
	 */
	@Override
	public void setOffLimitsUserLogin(String member_id) {
		memberMapper.updateOffLimitsDate(member_id);

	}

	/**
	 * 유저 로그인 제한일 초기화
	 */
	@Override
	public void initOffLimitsUserLogin(String member_id) {
		memberMapper.initOffLimitsDate(member_id);

	}

	/**
	 * 유저 로그인 제한일 가져오기
	 */
	@Override
	public Date getUserOffLimitsDate(String member_id) {

		return memberMapper.selectOffLimitsDate(member_id);
	}

	/**
	 * 디비 기준 현재 시간
	 */
	@Override
	public Date getTime() {

		return timeMapper.getNow();
	}

	/**
	 * 로그인 성공시 마지막 로그인 시간 업데이트
	 */
	@Override
	public void setLastLoginDate(String member_id) {
		memberMapper.updateLoginDate(member_id);

	}

	/**
	 * 회원 등급 가져오는 메서드
	 */
	@Override
	public String getUserAuth(String member_id) {

		return memberMapper.selectAuth(member_id);
	}

	/**
	 * 회원 주소 가져오는 메서드
	 */
	@Override
	public Map<String, Object> getUserAddress(String member_id) {

		return memberMapper.selectAddress(member_id);
	}

	// 회원 등급 정보 가져오기
	@Override
	public Map<String, Object> getUserGradeInfo(String member_id) {

		return memberMapper.selectGradeInfo(member_id);
	}

	// 탈퇴 회원인지 확인
	@Override
	public String checkWithDrawal(String member_id) {

		return memberMapper.selectWithdrawal(member_id);
	}

	// 탈퇴
	@Override
	public int doWithDrawal(String member_id) {

		return memberMapper.updateWithdrawal(member_id);
	}

	// 회원 정보
	@Override
	public MemberVO getMemberInfo(String member_id) {

		return memberMapper.selectMemberInfo(member_id);
	}

	// 로그인시 비밀번호 초기화 상태인지 확인
	@Override
	public String getInitPw(String member_id) {

		return memberMapper.selectInitPw(member_id);
	}

}
