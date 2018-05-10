package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class loginAspect {

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doverify() {
        System.out.println("11");
    }
}
