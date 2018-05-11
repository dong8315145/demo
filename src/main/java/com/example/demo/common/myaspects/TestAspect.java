package com.example.demo.common.myaspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-11 09:39
 **/
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void dotest() {
        System.out.println("TestAspect");
    }
}
