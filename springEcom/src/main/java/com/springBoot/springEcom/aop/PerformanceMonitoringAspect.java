package com.springBoot.springEcom.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoringAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Around("execution(* com.springBoot.springEcom.service.ProductService.getProductById(..))")
    public Object monitorPerformance(ProceedingJoinPoint pjt) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjt.proceed();
        long end = System.currentTimeMillis();
        LOGGER.info("time taken to execute method {} = {} ms", pjt.getSignature().getName(), end - start);
        return obj;
    }
}
