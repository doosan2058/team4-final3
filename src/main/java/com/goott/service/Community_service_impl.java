package com.goott.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goott.domain.DrawVO;
import com.goott.domain.T_board_VO;
import com.goott.domain.T_camping_VO;
import com.goott.mapper.Community_mapper;

@Service
public class Community_service_impl implements Community_service{

	@Autowired
	Community_mapper mapper;
	
	@Override
	public List<T_board_VO> getList() {
		return mapper.getList();
	}

	@Override
	public List<T_camping_VO> getData() {
		return mapper.getData();
	}

	@Override
	public List<Map<String, Object>> getDraw() {
		return mapper.getDraw();
	}

}
