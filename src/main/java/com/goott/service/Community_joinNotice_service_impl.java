package com.goott.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goott.domain.T_camping_VO;
import com.goott.domain.T_camping_reply_VO;
import com.goott.mapper.Community_joinNotice_mapper;

@Service
public class Community_joinNotice_service_impl implements Community_joinNotice_service {
	
	@Autowired
	private Community_joinNotice_mapper mapper;

	@Override
	public List<T_camping_VO> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public T_camping_VO getDetail(int camping_id) {
		return mapper.getDetail(camping_id);
	}

	@Override
	public void input(Map<String, Object> map) {
		mapper.input(map);
	}

	@Override
	public int correct(T_camping_VO camping) {
		return mapper.correct(camping);
	}

	@Override
	public int delete(int camping_id) {
		return mapper.delete(camping_id);
		
	}

	@Override
	public List<T_camping_reply_VO> replyList(int camping_id) {
		return mapper.replyList(camping_id);
	}

	@Override
	public T_camping_reply_VO replyCount(int camping_id) {
		return mapper.replyCount(camping_id);
	}

	@Override
	public void reply_input(Map<String, Object> map) {
		mapper.replyInput(map);
	}

	@Override
	public T_camping_reply_VO getReply(String reply_id) {
		return mapper.getReply(reply_id);
	}

	@Override
	public List<T_camping_reply_VO> viewMore(Map<String, Object> map) {
		return mapper.viewMore(map);
	}

	@Override
	public void increase(int camping_id) {
		mapper.increase(camping_id);
	}

	@Override
	public T_camping_VO listCount(Map<String, Object> map) {
		return mapper.listCount(map);
	}

	@Override
	public List<T_camping_VO> changePage(Map<String, Object> map) {
		return mapper.changePage(map);
	}

	@Override
	public List<Map<String, Object>> campsite(Map<String, Object> map) {
		return mapper.campsite(map);
	}

	@Override
	public void delete_reply(Map<String, Object> map) {
		mapper.delete_reply(map);
	}

	@Override
	public String adminConfirmation(String member_id) {
		return mapper.adminConfirmation(member_id);
	}

	@Override
	@Transactional
	public void inputPoint(Map<String, Object> map) {
		mapper.inputPoint(map);
		mapper.updateGrade(map);
	}

	@Override
	public String getImg(String member_id) {
		return mapper.getImg(member_id);
	}

}
