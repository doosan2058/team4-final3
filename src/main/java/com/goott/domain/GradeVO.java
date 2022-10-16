package com.goott.domain;

import lombok.Data;

@Data
public class GradeVO {
	/**
	 * grade_id int unsigned primary key auto_increment comment "등급 pk",
	 * grade_start_point int unsigned not null comment "시작 포인트",
	 * grade_end_point int unsigned not null comment "끝 포인트",
	 * grade_name varchar(10) not null comment "이름",
	 * grade_color varchar(10) not null unique comment "색",
	 * grade_discount double not null comment "할인율(0.1 ~ 1)",
	 * grade_accrual_rate double not null comment "적립율(0.1 ~ 1)",
	 * grade_comment varchar(50) not null comment "등급 설명(50자)",
	 * grade_img_url varchar(100) not null comment "등급 이미지 주소"
	 */
	
	int grade_id;
	int grade_start_point;
	int grade_end_point;
	String grade_name;
	String grade_color;
	double grade_discount;
	double grade_accrual_rate;
	String grade_comment;
	String grade_img_url;
	String grade_font_color;
	int gradeCount;
	
	
}
