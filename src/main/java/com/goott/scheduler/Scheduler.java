package com.goott.scheduler;

import com.goott.service.SchedulerService;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Log4j
public class Scheduler {
    @Inject
    SchedulerService schedulerService;
    @Scheduled(cron = "0 0 0 * * *") // 매일 새벽 00:00:00 작업
    public void autoUpdate(){
        // 회원 상품 구매시 예정 도착 시간이 지나면 상품 배송상태를 배송중 -> 배송완료 로 업데이트
        schedulerService.autoUpdateDeliveryState();
    }
}
