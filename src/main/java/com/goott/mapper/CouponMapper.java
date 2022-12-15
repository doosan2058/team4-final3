package com.goott.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.CouponVO;

@Mapper
public interface CouponMapper {
    /**
     * 쿠폰 정보 가져오기
     *
     * @param coupon_num 쿠폰 번호
     * @return 쿠폰 엔티티
     */
    public CouponVO selectCoupon(String coupon_num);

    /**
     * 쿠폰 사용하기
     * 쿠폰 미사용 > 사용 으로 업데이트
     *
     * @param coupon_num 쿠폰 번호
     */
    public void updateCouponLive(String coupon_num);
}
