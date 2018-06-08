package com.example.demo.common.myaspects;

import com.example.demo.common.constant.CookieConstant;
import com.example.demo.common.units.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-11 09:39
 **/
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))" + "&& !execution(public * com.example.demo.controller.CommonController.*.*(..))")
    public void verify() {


    }

    @Before("verify()")
    public void doverify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {

        }
        //去redis里查
        //String tokenValue=
        System.out.println("TestAspect");
    }
}
