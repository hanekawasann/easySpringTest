package com.yukms.easytest.test.mock;

import java.lang.reflect.Method;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

/**
 * mock切面
 *
 * @author yukms 763803382@qq.com 2019/3/26.
 */
@Log4j2
public class MockAspectJ {
    protected Object mockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        if (DataMocker.isGetMockData(method)) {
            log.info(joinPoint.getSignature().toString() + "尝试获取Mock数据");
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
