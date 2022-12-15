package com.goott.service;

import com.goott.domain.CouponVO;

public interface CouponService {

    /**
     * 쿠폰 정보 가져오기
     *
     * @param coupon_num 쿠폰 번호
     * @return 쿠폰 엔티티
     */
    public CouponVO getCouponInfo(String coupon_num);

    /**
     * 쿠폰 사용하기
     * 1. 사용 가능한 쿠폰 입니다.
	 * 2. 이미 사용된 쿠폰입니다.
	 * 3. 사용기한이 만료된 쿠폰입니다.
	 * 4. 쿠폰 번호를 잘못 입력 하였습니다.
	 * 5. 이 상품에는 사용할 수 없는 쿠폰 입니다.
     *
     * @param coupon_num 쿠폰 번호
     * @return 사용 결과 text
     */
    public String checkCoupon(String coupon_num, int product_category_id, int product_brand_id);

    /**
     * 쿠폰 사용하기 메서드
     * 미사용 > 사용 으로 업데이트
     *
     * @param coupon_num 쿠폰 번호
     */
    public void useCoupon(String coupon_num);
}
