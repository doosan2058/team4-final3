package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawResultVO;
import com.goott.domain.DrawVO;
import com.goott.domain.ProductVO;


@Mapper
public interface DrawAdminMapper {


	public List<DrawVO> draw_admin();
	public List<DrawVO> draw_admin_change(Map<String, Object> map);

	public List<Map<String, Object>> draw_admin_add_plus_product(Map<String,Object> map);

	public List<Map<String,Object>> draw_admin_add_plus(int product_category_id);
	
	/** 
	 * 
	 * @param category_id,brand_id
	 * @return 상품 이름 (admin_add에 상품이름 추가)
	 */


	public List<Map<String, Object>> admin_add_plus_product_name(Map<String,Object> map);
	/**
	 * 
	 * @param category_id,brand_id
	 * @return 상품 이름 (admin_add에 상품가격 추가)
	 */

	public List<Map<String, Object>> admin_add_plus_product_price(Map<String,Object> map);

	public void draw_admin_add(DrawVO vo);
 

	public List<Map<String, Object>> admin_title_product_id(Map<String, Object> map);

	public Integer set_draw_id(Integer product_id);

	public void update(DrawVO vo);
	public void delete(DrawVO vo);
	public List<DrawEnterVO> admin_count(Map<String, Object> map);
	public List<Map<String, Object>> draw_customer_memberID(Map<String, Object> map);
	public void draw_admin_count(Map<String, Object> map);

	public List<DrawVO> draw_customer();
	public int draw_customer_button_check(Map<String, Object> map);
	public void draw_customer_button(Map<String, Object> map);
	public List<DrawResultVO> getRusultID();
	public List<Map<String, Object>> admin_title_product_img(Map<String, Object>map);
	public void getdrawlist(Map<String, Object> map2);
	public List<Map<String, Object>> customer_draw_event(int draw_id);
	public int draw_admin_button_check(Map<String, Object> map);
	/**
	 * 당첨된 회원 이벤트 테이블에서 삭제
	 * @param map draw_id, member_id
	 */
	public int deleteWinningMember(Map<String, Object> map);
	public int winningCustomerCheck(Map<String, Object> map);
	
 
	
   
 
 
 
}
