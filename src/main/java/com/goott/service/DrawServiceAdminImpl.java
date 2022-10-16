package com.goott.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawResultVO;
import com.goott.domain.DrawVO;
import com.goott.domain.ProductVO;
import com.goott.mapper.DrawAdminMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class DrawServiceAdminImpl implements DrawServiceAdmin {

	@Inject
	DrawAdminMapper drawAdminMapper;

	@Override
	public List<DrawVO> draw_admin() {
		
		return drawAdminMapper.draw_admin();
	}

	@Override
	public List<DrawVO> draw_admin_change(Map<String, Object> map) {
		
		return drawAdminMapper.draw_admin_change(map);
	}

	@Override
	public List<Map<String, Object>> admin_add_plus_product(Map<String, Object> map) {

		
		return drawAdminMapper.draw_admin_add_plus_product(map);
	}

	@Override
	public List<Map<String, Object>> admin_add_plus(int product_category_id) {
	
		return drawAdminMapper.draw_admin_add_plus(product_category_id);
	}

	@Override
	public List<Map<String, Object>> admin_add_plus_product_name(Map<String, Object> map) {
		
		return drawAdminMapper.admin_add_plus_product_name(map);
	}

	@Override
	public List<Map<String, Object>> admin_add_plus_product_price(Map<String, Object> map) {
		
		return drawAdminMapper.admin_add_plus_product_price(map);
	}

	@Override
	public List<Map<String, Object>> admin_title_product_id(Map<String, Object> map) {
		
		return drawAdminMapper.admin_title_product_id(map);
	}

	@Override
	public void draw_admin_add(DrawVO vo) {
	
		drawAdminMapper.draw_admin_add(vo);
	}

	@Override
	public Integer set_draw_id(Integer id) {
		
		// System.out.println("service"+vo.toString());

		return drawAdminMapper.set_draw_id(id);
	}

	@Override
	public void update(DrawVO vo) {
		
		drawAdminMapper.update(vo);
	}

	@Override
	public void delete(DrawVO vo) {
	
		drawAdminMapper.delete(vo);
	}

	@Override
	public List<DrawEnterVO> admin_count(Map<String, Object> map) {
	
		System.out.println("이건 서비스야" + map);
		return drawAdminMapper.admin_count(map);

	}

	/*
	 * @Override public void admin_count_vo(DrawResultVO vo) { // TODO
	 * Auto-generated method stub drawAdminMapper.admin_count_vo(vo); }
	 */
	@Override
	public List<Map<String, Object>> draw_customer_memberID(Map<String, Object> map) {
		
		return drawAdminMapper.draw_customer_memberID(map);
	}

	@Override
	public void draw_admin_count(Map<String, Object> map) {
		
		drawAdminMapper.draw_admin_count(map);
	}

	@Override
	public List<DrawVO> draw_customer() {
		
		return drawAdminMapper.draw_customer();
	}

	@Override
	public int draw_customer_button_check(Map<String, Object> map) {
	
		return drawAdminMapper.draw_customer_button_check(map);
	}

	@Override
	public void draw_customer_button(Map<String, Object> map) {
	
		drawAdminMapper.draw_customer_button(map);
	}

	@Override
	public List<DrawResultVO> getResultList() {
		
		System.out.println("서비스 아이디 체크" );
		return drawAdminMapper.getRusultID(); 
	} 
	
	  @Override public List<Map<String, Object>>
	  admin_title_product_img(Map<String, Object> map) { 
		  
		  return drawAdminMapper.admin_title_product_img(map); 
		  }

	@Override
	public void getdrawlist(Map<String, Object> map2) {
		
		drawAdminMapper.getdrawlist(map2);
	}

	@Override
	public List<Map<String, Object>> customer_draw_event(int draw_id) {
	
		return drawAdminMapper.customer_draw_event(draw_id);
	}

	@Override
	public int draw_admin_button_check(Map<String, Object> map) {
		
		return drawAdminMapper.draw_admin_button_check(map);
	}

	@Override
	public void deleteWinningMember(Map<String, Object> map) {
		
		int result = drawAdminMapper.deleteWinningMember(map);
//		log.info("삭제 결과 : " + result);
	}

	@Override
	public int winningCustomerCheck(Map<String, Object> map) {
		
		return drawAdminMapper.winningCustomerCheck(map);
	}

	 

}
