package com.ecommerce.backend.utilities.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.ecommerce.backend.service.*.*(..))")
    public void logInfoMethodCalled(JoinPoint joinPoint) {
        logger.info("Service method called: " +  joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.ecommerce.backend.service.*.*(..))")
    public void logInfoMethodExecutedSuccessfully(JoinPoint joinPoint) {
        logger.info("Service method executed successfully: " +  joinPoint.getSignature().getName());
    }

    @After("execution(* com.ecommerce.backend.service.*.*(..))")
    public void logInfoMethodExecuted(JoinPoint joinPoint) {
        logger.info("Service method executed: " +  joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.ecommerce.backend.service.*.*(..))", throwing = "ex")
    public void logExceptionMethodExecuted(JoinPoint joinPoint, Exception ex) {
        logger.error(ex.getMessage(), ex);
    }
}
