package com.goott.domain;

import lombok.Data;

@Data
public class GradeVO {
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
