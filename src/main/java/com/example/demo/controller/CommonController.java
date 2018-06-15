package com.example.demo.controller;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.dao.SysUser;
import com.example.demo.repository.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录功能，修改密码，登出功能。主要是那些不用token的
 */
@Slf4j
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    SysUserRepository sysUserRepository;

    @PostMapping(value = "login")
    public ModelAndView login(HttpServletRequest request, SysUser sysUser) {
        ModelAndView modelAndView = new ModelAndView();
        SysUser sysUser1 = sysUserRepository.findByNameAndPasswordAndFlag(sysUser.getName(), sysUser.getPassword(), CommonConstants.VALID);
        if (sysUser1 == null) {
            sysUser1 = sysUserRepository.findByPhoneAndPasswordAndFlag(sysUser.getName(), sysUser.getPassword(), CommonConstants.VALID);
            if (sysUser1 != null) {
                request.getSession().setAttribute("id",sysUser1.getId());
                request.getSession().setAttribute("username",sysUser1.getName());
                modelAndView.setViewName("index");
                return modelAndView;
            }else{
                //抛导常
            }
        }
        request.getSession().setAttribute("id",sysUser1.getId());
        request.getSession().setAttribute("username",sysUser1.getName());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
