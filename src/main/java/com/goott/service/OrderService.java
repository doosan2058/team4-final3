package com.goott.service;

import java.util.List;
import java.util.Map;

import com.goott.domain.OrderVO;

public interface OrderService {
	/**
	 * 상품 주문하기 프로시저 호출 
	 * @param orderVO
	 * @param member_purchase_point
	 * @return
	 */
	public String buyProduct(OrderVO orderVO, int member_purchase_point);
	
	/**
	 * 회원별 현재 구매 정보 목록 가져오기
	 * @param member_id 회원 아이디
	 * @return OrderVO 엔티티
	 */
	public List<OrderVO> getOrderList(String member_id);
	

	/**
	 * 상품 구매 확정 메서드
	 * @param order_id 주문번호
	 * @return result 
	 * 		   0, 정상, "주문확정 하였습니다. 리뷰를 작성해 보세요."
	 * 		  -1, 에러, "죄송합니다. 구매 확정 처리중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요." 
	 *        -2, 이미 주문확정 or 주문취소 처리됨, "구매 확정 할수 없는 주문입니다. 관리자에게 문의해 주세요." 
	 */
	public String confirmProduct(int order_id);
}
