package com.goott.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goott.domain.CouponVO;
import com.goott.domain.OrderVO;
import com.goott.service.CouponService;
import com.goott.service.MemberService;
import com.goott.service.OrderService;
import com.goott.service.ProductService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j

public class OrderController {

    @Inject
    MemberService memberService;
    @Inject
    ProductService productService;
    @Inject
    CouponService couponService;
    @Inject
    OrderService orderSerivce;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderGet(HttpServletRequest request, @RequestParam String product_id,
                           @RequestParam(defaultValue = "1") String order_quantity, Model model) {

        HttpSession session = request.getSession();
        // 세션에 저장된 아이디
        String member_id = session.getAttribute("login_id").toString();


        // 등급 정보 가져오기
        Map<String, Object> grade = memberService.getUserGradeInfo(member_id);

        // 주소 가져오기
        Map<String, Object> list = memberService.getUserAddress(member_id);

        // 상품 번호, 재고량, 상품 이름, 상품 가격, 썸네일 이미지, 배송일
        Map<String, Object> product = productService.getOrderDetailInfo(Integer.parseInt(product_id));

        model.addAttribute("list", list);
        model.addAttribute("product", product);
        model.addAttribute("order_quantity", order_quantity);
        model.addAttribute("grade", grade);

        return "/shop/order/order_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/order/coupon", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> useCoupon(@RequestBody Map<String, Object> param) {


        String coupon_num = param.get("coupon_num").toString();
        int product_category_id = Integer.parseInt(param.get("product_category_id").toString());
        int product_brand_id = Integer.parseInt(param.get("product_brand_id").toString());

        String result = couponService.checkCoupon(coupon_num, product_category_id, product_brand_id);


        CouponVO couponVO = null;
        // 만약 쿠폰이 사용가능 하다면
        if (result.equals("사용 가능한 쿠폰 입니다.")) {
            // 쿠폰 정보도 저장
            couponVO = couponService.getCouponInfo(coupon_num);

        }

        // 쿠폰 사용 가능 여부와 쿠폰의 내용 json 형식으로 리턴
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("coupon", couponVO);

        return map;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(HttpServletRequest request, @RequestParam double getPoint, OrderVO orderVO, Model model) {


        // 구매자 아이디 세션에서 받아오기
        orderVO.setMember_id(request.getSession().getAttribute("login_id").toString());

        // 배송 요청사항 없으면 초기화
        if (orderVO.getOrder_comment().length() == 0 || orderVO.getOrder_comment() == null)
            orderVO.setOrder_comment("no comment");

        // 쿠폰 입력 없으면 초기화
        if (orderVO.getOrder_coupon_num().length() == 0 || orderVO.getOrder_coupon_num() == null)
            orderVO.setOrder_comment("no use coupon");

        // 적립 예정 포인트 int 형으로 초기화
        int member_purchase_point = (int) getPoint;

        // 구매 처리
        String resultText = orderSerivce.buyProduct(orderVO, member_purchase_point);

        if (resultText.equals("상품을 구매하였습니다.")) {
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/shop");
        } else {
            String query1 = Integer.toString(orderVO.getProduct_id());
            String query2 = Integer.toString(orderVO.getOrder_quantity());
            model.addAttribute("msg", resultText);
            model.addAttribute("url", "/order?product_id=" + query1 + "&" + "order_quantity=" + query2);
        }


        return "/common/alert";
    }

    @RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
    public String confirmPurchase(@RequestParam int order_id, Model model) {

        String result = orderSerivce.confirmProduct(order_id);
        model.addAttribute("msg", result);
        model.addAttribute("url", "/user/mypage");

        return "/common/alert";
    }

}
