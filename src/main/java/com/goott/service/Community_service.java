package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.T_board_VO;
import com.goott.domain.T_camping_VO;

public interface Community_service {

	List<T_board_VO> getList();

	List<T_camping_VO> getData();

	Map<String, Object> getDraw();

}
