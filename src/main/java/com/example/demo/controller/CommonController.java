package com.example.demo.controller;

import com.example.demo.dao.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录功能，修改密码，登出功能。主要是那些不用token的
 */
@Slf4j
@Controller
@RequestMapping("/common")
public class CommonController {

    @PostMapping("/login")
    public ModelAndView login(Member member){
        ModelAndView modelAndView=new ModelAndView();
        //TODO 查数据库,返回用户数据（用户，菜单）
        modelAndView.addObject("member" ,member);
        modelAndView.setViewName("redirect:/welcome/index");
        return modelAndView;
    }
}
