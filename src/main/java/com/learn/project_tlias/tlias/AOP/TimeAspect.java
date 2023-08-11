package com.learn.project_tlias.tlias.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@Aspect
public class TimeAspect {
    @Around("execution(* com.learn.project_tlias.tlias.service.*.*(..))")
    public Object recordTime (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info(proceedingJoinPoint.getSignature()+"耗时："+(end - begin)+"ms");
        return object;

    }
}
