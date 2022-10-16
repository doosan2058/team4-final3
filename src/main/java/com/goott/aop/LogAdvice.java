package com.goott.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Aspect
@Component
public class LogAdvice {

//    @Around("execution(* com.goott.*.*.*(..))")
//    public Object logAround(ProceedingJoinPoint pjp) {
//
//
//        log.info("클래스 : " + pjp.getTarget());
//        log.info("메서드 : " + pjp.getSignature().getName());
//        log.info("파마리터 : " + Arrays.toString(pjp.getArgs()));
//
//
//        Object result = null;
//
//        try {
//            result = pjp.proceed();
//        } catch (Throwable e) {
//
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
