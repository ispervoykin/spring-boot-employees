package com.ilya.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.ilya.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.ilya.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.ilya.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forApp() {}

    @Before("forApp()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=========>> In @Before, calling method: " + method);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("=========>> " + arg);
        }
    }

    @AfterReturning(pointcut = "forApp()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=========>> In @AfterReturning, calling method: " + method);

        logger.info("=========>> Result: " + result);
    }
}
