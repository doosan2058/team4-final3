package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SessionVO {
	private int idx; // 세션 번호
	private String browser; // 브라우저 이름
	private String ip; // 접속 아이피 주소
	private Date create_time; // 접속 시간
}
