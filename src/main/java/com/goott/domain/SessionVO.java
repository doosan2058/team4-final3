package com.goott.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SessionVO {
	int idx;
	String browser;
	String ip;
	Date create_time;
}
