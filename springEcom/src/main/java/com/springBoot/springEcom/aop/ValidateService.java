package com.springBoot.springEcom.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ValidateService.class);

    @Around("execution(* com.springBoot.springEcom.controller.ProductController.getProduct(..) && args(id))")
    public Object validateParameters(ProceedingJoinPoint pjt,int id) throws Throwable {
        if(id<0){
            LOGGER.info("postid is negative converting it to positive");
            id=-id;
        }
        Object obj = pjt.proceed(new Object[]{id});
        return obj;
    }
}
