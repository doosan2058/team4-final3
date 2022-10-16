package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	
	String member_id;
	String member_pw;
	String member_email;
	String member_name;
	String member_phone;
	int member_age;
	String member_gender;
	String member_postal_code;
	String member_address;
	Date member_join_date;
	Date member_last_login_date;
	String member_state;
	String member_withdrawal;
	String member_email_agree;
	String member_service_agree;
	String member_personal_agree;
	int grade_id;
	int member_purchase_point;
	int member_write_point;
	String member_profile_img_url;
	int member_login_count;
	Date member_off_limits_date;
	String member_init_pw;
	String grade_name;
	
	
}
