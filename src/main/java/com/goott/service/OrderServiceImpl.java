package com.goott.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goott.domain.OrderVO;
import com.goott.mapper.OrderMapper;
import com.goott.mapper.ProductMapper;

import lombok.extern.log4j.Log4j;

@Transactional
@Service
@Log4j
public class OrderServiceImpl implements OrderService {

    @Inject
    ProductService productService;
    @Inject
    CouponService couponService;
    @Inject
    OrderMapper orderMapper;
    @Inject
    ProductMapper productMapper;


    public boolean checkProductStock(OrderVO orderVO) {
        //상품 아이디
        int product_id = orderVO.getProduct_id();
        //구매하려는 상품 개수
        int order_quantity = orderVO.getOrder_quantity();
        //실제 상품 재고 개수
        int product_stock = productMapper.selectStock(product_id);


        if ((product_stock - order_quantity) < 0) { // 구매시 상품 개수 음수 > 재고 부족
            return false;
        } else
            return true;
    }

    public int doBuyProcedure(OrderVO orderVO, int member_purchase_point) {
        Map<String, Object> buyMap = new HashMap<>();
        buyMap.put("in_member_id", orderVO.getMember_id());
        buyMap.put("in_product_id", orderVO.getProduct_id());
        buyMap.put("in_order_quantity", orderVO.getOrder_quantity());
        buyMap.put("in_order_address", orderVO.getOrder_address());
        buyMap.put("in_order_purchase_amount", orderVO.getOrder_purchase_amount());
        buyMap.put("in_order_coupon_num", orderVO.getOrder_coupon_num());
        buyMap.put("in_order_comment", orderVO.getOrder_comment());
        buyMap.put("in_member_purchase_point", member_purchase_point);
        orderMapper.buy(buyMap);

        //result : 프로시저 결과 out 파라미터, 성공 : 0 / sql오류 : -1 / 재고부족 : -2
        int resultNum = Integer.parseInt(buyMap.get("result").toString());

        return resultNum;
    }

    // 상품 구매하기
    @Override
    public String buyProduct(OrderVO orderVO, int member_purchase_point) {
        //상품 재고 체크
        if (!checkProductStock(orderVO)) {
            return "죄송합니다. 상품의 재고가 부족합니다. 관리자에게 문의해 주세요.";
        }

        // t_order 테이블에 insert 전 쿠폰 사용 가능 여부 다시 확인
        // 상품 카테고리, 브랜드 번호 가져오기
        Map<String, Object> productMap = productService.getProductCidBid(orderVO.getProduct_id());
        int product_category_id = Integer.parseInt(productMap.get("product_category_id").toString());
        int product_brand_id = Integer.parseInt(productMap.get("product_brand_id").toString());

        // 사용할 쿠폰 번호
        String coupon_num = orderVO.getOrder_coupon_num();
        // 쿠폰 사용 가능 여부 조회
        String result = couponService.checkCoupon(coupon_num, product_category_id, product_brand_id);

        // 쿠폰을 사용하지 않았으면
        if (result.equals("쿠폰을 사용하지 않았습니다.")) {
            int resultNum = doBuyProcedure(orderVO, member_purchase_point);

            if (resultNum == 0)
                return "상품을 구매하였습니다.";
            else if (resultNum == -1) {
                return "죄송합니다. 상품 구매중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.";
            } else if (resultNum == -2) {
                return "죄송합니다. 상품의 재고가 부족합니다. 관리자에게 문의해 주세요.";
            } else
                return "심각한 오류 발생";
        }

        // 사용가능 한 쿠폰이면 쿠폰 사용하기
        else if (result.equals("사용 가능한 쿠폰 입니다.")) {
            // 쿠폰 사용 : 쿠폰 live 미사용 -> 사용 으로 업데이트
            couponService.useCoupon(coupon_num);
            int resultNum = doBuyProcedure(orderVO, member_purchase_point);
            if (resultNum == 0)
                return "상품을 구매하였습니다.";
            else if (resultNum == -1) {

                return "죄송합니다. 상품 구매중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.";
            } else if (resultNum == -2) {
                return "죄송합니다. 상품의 재고가 부족합니다. 관리자에게 문의해 주세요.";
            } else
                return "심각한 오류 발생";
        }
        // 사용 가능 쿠폰이 아니면 해당 내용 리턴
        else {
            return result;
        }

    }

	@Override
    public List<OrderVO> getOrderList(String member_id) {

        return orderMapper.selectOrderAll(member_id);
    }

    @Override
    public String confirmProduct(int order_id) {

        Map<String, Object> confirmMap = new HashMap<>();
        confirmMap.put("in_order_id", order_id);
		orderMapper.updateOrder(confirmMap);
		int resultNum = Integer.parseInt(confirmMap.get("result").toString()); // result > 프로시저 결과값

		if (resultNum == 0) {
            return "주문확정 하였습니다. 리뷰를 작성해 보세요.";
        } else if (resultNum == -1) {
            return "죄송합니다. 구매 확정 처리중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.";
        } else if (resultNum == -2) {
            return "구매 확정 할수 없는 주문입니다. 관리자에게 문의해 주세요.";
        } else
            return "심각한 오류 발생.";
    }

}
