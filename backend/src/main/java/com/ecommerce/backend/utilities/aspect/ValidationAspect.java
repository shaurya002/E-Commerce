package com.ecommerce.backend.utilities.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.ecommerce.backend.service.*.*(..))")
    public Object validateMethodArguments(ProceedingJoinPoint joinPoint, Object [] args) throws Throwable {
        for (Object arg : args) {
            if (arg == null) {
                logger.error("Validation failed: Argument is null");
                throw new IllegalArgumentException("Argument cannot be null");
            }
        }
        Object returnValue = joinPoint.proceed(args);
        return returnValue;
    }
}
