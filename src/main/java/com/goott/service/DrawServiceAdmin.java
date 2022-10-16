package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawResultVO;
import com.goott.domain.DrawVO;
import com.goott.domain.ProductVO;
import com.goott.domain.T_board_VO;

public interface DrawServiceAdmin {
	
	public List<DrawVO> draw_admin();
	public List<DrawVO> draw_admin_change(Map<String, Object> map);
	public List<Map<String,Object>> admin_add_plus(int product_category_id);
	public List<Map<String, Object>> admin_add_plus_product(Map<String,Object> map);
	public List<Map<String, Object>> admin_add_plus_product_name(Map<String,Object> map);
	public List<Map<String, Object>> admin_add_plus_product_price(Map<String,Object> map);
	


	public List<Map<String, Object>> admin_title_product_id(Map<String, Object> map);
	public void draw_admin_add(DrawVO vo);
	public Integer set_draw_id(Integer id);
	public void update(DrawVO vo);
	public void delete(DrawVO vo); 
	public 	List<DrawEnterVO> admin_count(Map<String, Object> map); 
	/* public void admin_count_vo(DrawResultVO vo); */
	public List<Map<String, Object>> draw_customer_memberID(Map<String, Object> map);
	public void draw_admin_count(Map<String, Object> map);
	
	public List<DrawVO> draw_customer();
	public int draw_customer_button_check(Map<String, Object> map);
	public void draw_customer_button(Map<String, Object> map);
	public List<DrawResultVO> getResultList();
	public List<Map<String, Object>> admin_title_product_img(Map<String, Object>map);
	public void getdrawlist(Map<String, Object> map2);
	public List<Map<String, Object>> customer_draw_event(int draw_id);
	public int draw_admin_button_check(Map<String, Object> map);
	
	public void deleteWinningMember(Map<String, Object> map);

	public int winningCustomerCheck(Map<String, Object> map);
	 
	

 
  

} 
         