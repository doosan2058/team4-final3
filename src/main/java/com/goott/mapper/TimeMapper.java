package com.goott.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeMapper {
	
	/**
	 * 디비 기준 현재 시간 구하기
	 * @return 현재시간
	 */
	public Date getNow();
}
