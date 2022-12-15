package com.goott.domain;

import lombok.Data;

@Data
public class GradeVO {
	private int grade_id; // 등급 번호
	private int grade_start_point; // 등급 시작 포인트
	private int grade_end_point; // 등급 종료 포인트
	private String grade_name; // 등급 이름
	private String grade_color; // 등급 색
	private double grade_discount; // 등급 할인율
	private double grade_accrual_rate; // 등급 적립율
	private String grade_comment; // 등급 설명
	private String grade_img_url; // 등급 이미지 주소
	private String grade_font_color; // 등급 폰트 색
	private int gradeCount;
	
	
}
