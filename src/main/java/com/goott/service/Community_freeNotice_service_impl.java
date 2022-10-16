package com.goott.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goott.domain.T_board_VO;
import com.goott.domain.T_board_reply_VO;
import com.goott.domain.T_board_tag_VO;
import com.goott.mapper.Community_freeNotice_mapper;

@Service
public class Community_freeNotice_service_impl implements Community_freeNotice_service{
	
	@Autowired
	private Community_freeNotice_mapper mapper;

	@Override
	public List<T_board_VO> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public List<T_board_tag_VO> getTag() {
		return mapper.getTag();
	}

	@Override
	public void inputPost(T_board_VO board) {
		mapper.inputPost(board);
	}

	@Override
	public T_board_VO getDetail(int board_id) {
		return mapper.getDetail(board_id);
	}

	@Override
	public int correct(T_board_VO board) {
		return mapper.correct(board);
	}

	@Override
	public int delete(int board_id) {
		return mapper.delete(board_id);
	}

	@Override
	public List<T_board_reply_VO> replyList(int board_id) {
		return mapper.replyList(board_id);
	}

	@Override
	public void reply_input(Map<String, Object> map) {
		mapper.inputReply(map);
	}

	@Override
	public T_board_reply_VO replyCount(int board_id) {
		return mapper.replyCount(board_id);
	}

	@Override
	public T_board_reply_VO getReply(String reply_id) {
		return mapper.getReply(reply_id);
	}

	@Override
	public void increase(int board_id) {
		mapper.increase(board_id);
	}

	@Override
	public List<T_board_reply_VO> get_viewMore(Map<String, Object> map) {
		return mapper.get_viewMore(map);
	}

	@Override
	public T_board_VO listCount(Map<String, Object> map) {
		return mapper.listCount(map);
	}

	@Override
	public List<T_board_VO> change_page(Map<String, Object> map) {
		return mapper.change_page(map);
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
	public int recommend_check(Map<String, Object> map) {
		return mapper.recommend_check(map);
	}

	@Override
	public void recommend(Map<String, Object> map) {
		mapper.recommend(map);
	}

	@Override
	public void board_recommend(Map<String, Object> map) {
		mapper.board_recommend(map);
	}

	@Override
	@Transactional
	public void inputPoint(T_board_VO board) {
		mapper.inputPoint(board);
		mapper.updateGrade(board);
	}

	@Override
	public String getImg(String member_id) {
		return mapper.getImg(member_id);
	}

}
