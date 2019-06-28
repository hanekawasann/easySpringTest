package com.yukms.easytest.test.util;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 11:35
 */
public final class AspectJUtils {
    public static Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return ReflectionUtils
            .findMethod(joinPoint.getTarget().getClass(), signature.getName(), signature.getParameterTypes());
    }

    private AspectJUtils() { }
}
