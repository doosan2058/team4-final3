package com.goott.scheduler;

import com.goott.service.DrawService;
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
    @Inject
    DrawService drawService;
    @Scheduled(cron = "0 0 4 * * *", zone = "GMT+9:00") // 매일 작업, 리눅스 utc -> kst 시간 세팅
    public void autoUpdate(){
        // 회원 상품 구매시 예정 도착 시간이 지나면 상품 배송상태를 배송중 -> 배송완료 로 업데이트
        schedulerService.autoUpdateDeliveryState();
        // 이벤트 모집 종료일 지나면 이벤트 마감 업데이트
        drawService.autoUpdateDrawEnd();
    }
}
