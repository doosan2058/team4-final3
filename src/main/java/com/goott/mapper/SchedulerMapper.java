package com.goott.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerMapper {
    /**
     * 상품 배송상태 배송중 > 배송완료로 업데이트
     *
     */
    public void updateDeliveryState();
}
