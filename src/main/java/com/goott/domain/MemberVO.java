package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {


	private String member_id; // 회원 아이디
	private String member_pw; // 회원 비밀번호
	private String member_email; // 회원 이메일
	private String member_name; // 회원 이름
	private String member_phone; // 회원 핸드폰 번호
	private int member_age; // 회원 나이
	private String member_gender; // 회원 성별
	private String member_postal_code; // 회원 우편 번호
	private String member_address; // 회원 주소
	private Date member_join_date; // 회원 가입일
	private Date member_last_login_date; // 마지막 접속일
	private String member_state; // 회원 휴면 상태 여부
	private String member_withdrawal; // 회원 탈퇴 상태 여부
	private String member_email_agree; // 이메일 수신 동의 여부
	private String member_service_agree; // 이용약관 동의 여부
	private String member_personal_agree; // 개인정보 수집 동의 여부
	private int grade_id; // 등급 번호
	private int member_purchase_point; // 구매 포인트
	private int member_write_point; // 글 작성 포인트
	private String member_profile_img_url; // 프로필 이미지 주소
	private int member_login_count; // 로그인 시도 횟수
	private Date member_off_limits_date; // 로그인 제한일
	private String member_init_pw; // 비밀번호 초기화 여부
	private String grade_name; // 등급 이름
	
	
}
