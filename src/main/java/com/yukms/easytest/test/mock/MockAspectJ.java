package com.yukms.easytest.test.mock;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * mock切面
 *
 * @author yukms 763803382@qq.com 2019/3/26.
 */
@Aspect
@Component
public class MockAspectJ {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockAspectJ.class);

    @Around("(@within(org.springframework.stereotype.Service)"
        + "|| @within(org.springframework.stereotype.Component)"
        + "|| @within(org.springframework.stereotype.Repository))")
    private Object mockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        if (DataMocker.isGetMockData(method)) {
            LOGGER.info(joinPoint.getSignature().toString() + "尝试获取Mock数据");
            Object[] args = joinPoint.getArgs();
            return DataMocker.getData(method, args);
        }
        return joinPoint.proceed();
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return ReflectionUtils
            .findMethod(joinPoint.getTarget().getClass(), signature.getName(), signature.getParameterTypes());
    }
}
