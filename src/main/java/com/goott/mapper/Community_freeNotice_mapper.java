package com.goott.mapper;

import java.util.List;
import java.util.Map;

import com.goott.domain.T_board_VO;
import com.goott.domain.T_board_reply_VO;
import com.goott.domain.T_board_tag_VO;

public interface Community_freeNotice_mapper {

	public List<T_board_VO> getList(Map<String, Object> map);

	public List<T_board_tag_VO> getTag();

	public void inputPost(T_board_VO board);

	public T_board_VO getDetail(int board_id);

	public int correct(T_board_VO board);

	public int delete(int board_id);

	public List<T_board_reply_VO> replyList(int board_id);

	public void inputReply(Map<String, Object> map);

	public T_board_reply_VO replyCount(int board_id);

	public T_board_reply_VO getReply(String reply_id);

	public void increase(int board_id);

	public List<T_board_reply_VO> get_viewMore(Map<String, Object> map);

	public T_board_VO listCount(Map<String, Object> map);

	public List<T_board_VO> change_page(Map<String, Object> map);

	public void delete_reply(Map<String, Object> map);

	public String adminConfirmation(String member_id);

	public int recommend_check(Map<String, Object> map);

	public void recommend(Map<String, Object> map);

	public void board_recommend(Map<String, Object> map);

	public void inputPoint(T_board_VO board);

	public void updateGrade(T_board_VO board);

	public String getImg(String member_id);

}
