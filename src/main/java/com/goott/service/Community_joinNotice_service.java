package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.T_camping_VO;
import com.goott.domain.T_camping_reply_VO;

public interface Community_joinNotice_service {

	public List<T_camping_VO> getList(Map<String, Object> map);

	public T_camping_VO getDetail(int camping_id);

	public void input(Map<String, Object> map);

	public int correct(T_camping_VO camping);

	public int delete(int camping_id);

	public List<T_camping_reply_VO> replyList(int camping_id);

	public T_camping_reply_VO replyCount(int camping_id);

	public void reply_input(Map<String, Object> map);

	public T_camping_reply_VO getReply(String reply_id);

	public List<T_camping_reply_VO> viewMore(Map<String, Object> map);

	public void increase(int camping_id);

	public T_camping_VO listCount(Map<String, Object> map);

	public List<T_camping_VO> changePage(Map<String, Object> map);

	public List<Map<String,Object>> campsite(Map<String, Object> map);

	public void delete_reply(Map<String, Object> map);

	public String adminConfirmation(String member_id);

	public void inputPoint(Map<String, Object> map);

	public String getImg(String member_id);

}
