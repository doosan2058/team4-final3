package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.OrderVO;

@Mapper
public interface OrderMapper {
    /**
     * 주문하기
     * buy_product 프로시저 호출
     *
     * @param map (in_member_id, in_product_id, in_order_quantity, in_order_address, in_order_purchase_amount, in_order_coupon_num, in_order_comment, in_member_purchase_point)
     * @return map + result ( 0 정상, -1 에러, -2 재고부족)
     */
    public Map<String, Object> buy(Map<String, Object> map);


    /**
     * 회원별 구매중 목록 가져오기
     *
     * @param member_id 회원 아이디
     * @return OrderVO 엔티티
     */
    public List<OrderVO> selectOrderAll(String member_id);

    /**
     * 상품 구매 확정
     * confirm_product 프로시저 호출
     *
     * @param map 주문 번호
     * @return map + result ( 0 정상, -1 에러, -2 주문 상태 배송중이나 배송완료가 아님)
     */
    public Map<String, Object> updateOrder(Map<String, Object> map);

    /**
     * 구매 정보 가져오기
     *
     * @param sales_id 매출 번호
     * @return member_id, product_id
     */
    public Map<String, Object> selectReviewInfo(int sales_id);
}
