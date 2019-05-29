package com.yukms.easytest;

import com.yukms.easytest.test.mock.MockAspectJ;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yukms 763803382@qq.com 2019/5/29 14:18
 */
@Aspect
@Component
public class MyMockAspectJ extends MockAspectJ {
    @Around("(@within(org.springframework.stereotype.Service)"//
        + "|| @within(org.springframework.stereotype.Component)"//
        + "|| @within(org.springframework.stereotype.Repository))")
    @Override
    protected Object mockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.mockAround(joinPoint);
    }
}
