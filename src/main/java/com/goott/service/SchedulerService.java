package com.goott.service;

public interface SchedulerService {
    /**
     * 상품 배송상태 배송중 > 배송완료 업데이트
     *
     */
    public void autoUpdateDeliveryState();
}
