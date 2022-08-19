package com.example.hongber.common.aop;

import com.example.hongber.common.util.common.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Order(1)
@Component
public class LogAspect {
   @Around("(execution(* com.example.hongber..*Controller.*(..))"
         + " || execution(* com.example.hongber..*Service.*(..))"
         + " || execution(* com.example.hongber..*Repository.*(..))"
         + " || execution(* com.example.hongber..*Mapper.*(..))"
         + " || execution(* com.example.hongber..*Repository.*(..)))"
         + " && !@annotation(com.example.hongber.common.annotation.NoLog)")
   public Object logging(ProceedingJoinPoint pjp) throws Throwable {
       log.info("'{}.{}({})'", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), JsonUtil.toJson(pjp.getArgs()));
       return pjp.proceed();
   }
}