package com.example.demo.controller;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.dao.SysUser;
import com.example.demo.repository.SysUserRepository;
import com.example.demo.service.impl.SysMenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 登录功能，修改密码，登出功能。主要是那些不用token的
 */
@Slf4j
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysMenuServiceImpl sysMenuService;

    @PostMapping(value = "login")
    public ModelAndView login(HttpServletRequest request, SysUser sysUser) {
        ModelAndView modelAndView = new ModelAndView();
        SysUser temp=sysUserRepository.findByNameAndPasswordAndFlag(sysUser.getName(),sysUser.getPassword(),CommonConstants.VALID);
        if (temp!=null) {
            request.getSession().setAttribute("id",temp.getId());
            request.getSession().setAttribute("username",temp.getName());
            request.getSession().setAttribute("menu",sysMenuService.findTreeByPid(CommonConstants.SYS_MENU_ROOT));
            modelAndView.setViewName("index");
            return modelAndView;
        }else{
            modelAndView.setViewName("index");
        }
//        request.getSession().setAttribute("id",sysUser1.getId());
//        request.getSession().setAttribute("username",sysUser1.getName());
//        modelAndView.addObject("menu",sysMenuService.findTreeByPid(CommonConstants.SYS_MENU_ROOT));
//        modelAndView.setViewName("index");
        return modelAndView;
    }

}
