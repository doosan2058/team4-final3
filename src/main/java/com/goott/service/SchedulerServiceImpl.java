package com.goott.service;

import com.goott.mapper.SchedulerMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class SchedulerServiceImpl implements SchedulerService{
    @Inject
    SchedulerMapper schedulerMapper;
    @Override
    public void autoUpdateDeliveryState() {
        schedulerMapper.updateDeliveryState(); // 매일 00:00:00 실행되는 배송 완료 체크 메서드
    }
}
