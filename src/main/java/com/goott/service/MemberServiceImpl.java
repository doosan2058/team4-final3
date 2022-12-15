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

    @Inject
    MemberMapper memberMapper;

    @Inject
    TimeMapper timeMapper;


    @Override
    public void joinMember(MemberVO memberVO) {
        // 비밀번호 암호화
        String securePw = this.pwEncode(memberVO.getMember_pw());
        // 암호화한 비밀번호 vo에 저장
        memberVO.setMember_pw(securePw);
        // DAO로 vo 전송
        memberMapper.insertMember(memberVO);

    }


    public String pwEncode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password); // 암호화된 비밀번호

        return encodedPassword;

    }


    @Override
    public boolean checkPw(String member_id, String member_pw) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.getPassword(member_id);
        return passwordEncoder.matches(member_pw, encodedPassword); // 비밀번호 확인

    }

    @Override
    public String getPassword(String member_id) {

        return memberMapper.selectPw(member_id);
    }


    @Override
    public int checkDupId(String member_id) {

        return memberMapper.countId(member_id);

    }


    @Override
    public int checkDupEmail(String member_email) {

        return memberMapper.countEmail(member_email);
    }


    @Transactional
    @Override
    public String loginCheck(String member_id, String member_pw) {

        int dupIdCountTemp = this.checkDupId(member_id); // 아이디 존재 확인
        if (dupIdCountTemp == 0) {
            return "존재하지 않는 아이디 입니다.";
        }

        String tempWidhDrawal = this.checkWithDrawal(member_id); // 탈퇴 회원 확인
        if (tempWidhDrawal.equals("y"))
            return "탈퇴한 회원 입니다.";

        Date date = this.getUserOffLimitsDate(member_id);
        Date now = this.getTime();

        // 로그인 제한일 현재 시간과 비교
        // 현재 시간이 더 크다면 로그인 제한 횟수 초기화 후 진행
        // 로그인 제한일이 더 크다면 로그인 금지
        if (date != null) {
            // 음수 : now 가 작음 0 : 같음 양수 : now 가 큼
            int dateCount = now.compareTo(date);
            // 아직 제한일 안지났을시
            if (dateCount <= 0) {
                return "죄송합니다. 현재 해당 아이디는 로그인 제한 상태 입니다.";
            }
            // 제한일 이후 라면
            else if (dateCount > 0) {

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
                    this.addUserLoginCount(member_id); // 로그인 시도 횟수 + 1
                    int tempCount = this.getUserLoginCount(member_id); // 현재 로그인 시도 횟수
                    // 누적 5회면 로그인 제한
                    if (tempCount == 5) {
                        this.setOffLimitsUserLogin(member_id);
                        return "비밀번호 5회 불일치 하여 로그인을 일시 금지 합니다.";
                    }
                    // 누적 4회 이하
                    else
                        return "비밀번호가 일치하지 않습니다. 비밀번호 불일치 횟수 : (" + tempCount + ")회.";
                }
            }
            // 이외는 오류
            else
                return "심각한 오류 발생.";
        }
        else
            return "심각한 오류 발생.";
    }


    @Override
    public int getUserLoginCount(String member_id) {

        return memberMapper.selectLoginCount(member_id);
    }


    @Override
    public void addUserLoginCount(String member_id) {
        memberMapper.updateLoginCount(member_id);

    }


    @Override
    public void initUserLoginCount(String member_id) {
        memberMapper.initLoginCount(member_id);

    }


    @Override
    public void setOffLimitsUserLogin(String member_id) {
        memberMapper.updateOffLimitsDate(member_id);

    }


    @Override
    public void initOffLimitsUserLogin(String member_id) {
        memberMapper.initOffLimitsDate(member_id);

    }


    @Override
    public Date getUserOffLimitsDate(String member_id) {

        return memberMapper.selectOffLimitsDate(member_id);
    }


    @Override
    public Date getTime() {

        return timeMapper.getNow();
    }


    @Override
    public void setLastLoginDate(String member_id) {
        memberMapper.updateLoginDate(member_id);

    }


    @Override
    public String getUserAuth(String member_id) {

        return memberMapper.selectAuth(member_id);
    }


    @Override
    public Map<String, Object> getUserAddress(String member_id) {

        return memberMapper.selectAddress(member_id);
    }


    @Override
    public Map<String, Object> getUserGradeInfo(String member_id) {

        return memberMapper.selectGradeInfo(member_id);
    }


    @Override
    public String checkWithDrawal(String member_id) {

        return memberMapper.selectWithdrawal(member_id);
    }


    @Override
    public int doWithDrawal(String member_id) {

        return memberMapper.updateWithdrawal(member_id);
    }


    @Override
    public MemberVO getMemberInfo(String member_id) {

        return memberMapper.selectMemberInfo(member_id);
    }


    @Override
    public String getInitPw(String member_id) {

        return memberMapper.selectInitPw(member_id);
    }

}
