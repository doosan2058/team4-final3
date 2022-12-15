package com.goott.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.CouponVO;
import com.goott.mapper.CouponMapper;
import com.goott.mapper.TimeMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CouponServiceImpl implements CouponService {

    @Inject
    CouponMapper couponMapper;

    @Inject
    TimeMapper timeMapper;

    @Override
    public CouponVO getCouponInfo(String coupon_num) {

        return couponMapper.selectCoupon(coupon_num);
    }


    @Override
    public String checkCoupon(String coupon_num, int product_category_id, int product_brand_id) {

        if (coupon_num.equals("no use coupon"))
            return "쿠폰을 사용하지 않았습니다.";

        CouponVO couponVO = this.getCouponInfo(coupon_num);


        // 검색된 쿠폰이 없다면 존재 하지 않는 쿠폰
        if (couponVO == null)
            return "쿠폰 번호를 잘못 입력 하였습니다.";

        // 만약 사용된 쿠폰이라면 사용할수 없는 쿠폰
        if (couponVO.getCoupon_live().equals("사용"))
            return "이미 사용된 쿠폰입니다.";

        // 사용기간이 만료된 쿠폰이라면
        Date now = timeMapper.getNow();
        // now 가 end_date 보다 나중이면 true 리턴
        boolean result = now.after(couponVO.getCoupon_end_date());

		// true면
        if (result) {
            return "사용기한이 만료된 쿠폰입니다.";
        }
		// 쿠폰의 사용가능 카테고리, 브랜드와 (0 : 모든 상품 가능)
        // 상품의 카테고리, 브랜드가 일치하지 않는다면
		int couponCategory = couponVO.getCoupon_category();
        int couponBrand = couponVO.getCoupon_brand();
		// couponCategory : 0 , couponBrand : 0 >> 모든 상품 사용가능
        // 또는 couponCategory : 0 , couponBrand == 상품 브랜드
        // 또는 couponCategory == 상품 카테고리 , couponBrand : 0
        // 또는 couponCategory == 상품 카테고리 , couponBrand == 상품 브랜드
        if ((couponCategory == 0 && couponBrand == 0) ||
                (couponCategory == 0 && couponBrand == product_brand_id) ||
                (couponCategory == product_category_id && couponBrand == 0) ||
                (couponCategory == product_category_id && couponBrand == product_brand_id)) {
			//사용할수 있는 쿠폰
            return "사용 가능한 쿠폰 입니다.";
        }
        //카테고리나 브랜드가 틀리면 사용 불가
        else if (couponCategory != product_category_id || couponBrand != product_brand_id) {
            return "이 상품에는 사용할 수 없는 쿠폰 입니다.";
        } else
            return "심각한 오류 발생.";
	}


    @Override
    public void useCoupon(String coupon_num) {
        couponMapper.updateCouponLive(coupon_num); // 쿠폰 사용 업데이트
	}

}
