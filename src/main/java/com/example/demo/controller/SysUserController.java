package com.example.demo.controller;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.common.constant.CommonConstants;
import com.example.demo.common.enums.ResultEnum;
import com.example.demo.dao.SysUser;
import com.example.demo.repository.SysUserRepository;
import com.example.demo.service.impl.SysMenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    SysMenuServiceImpl sysMenuService;

    @PostMapping(value = "login")
    public ResultVO login(HttpServletRequest request, SysUser sysUser) {
        ResultVO resultVO =new ResultVO();
        sysUser.setFlag(CommonConstants.VALID);
        sysUser =sysUserRepository.findById(sysUser.getId()).get();
        if (!StringUtils.isEmpty(sysUser.getId())) {
             resultVO = new ResultVO(ResultEnum.SUCCESS);

        } else {
             resultVO = new ResultVO(ResultEnum.FAIL);
        }
        //查找菜单
        resultVO.setData(sysMenuService.findTreeByPid(CommonConstants.SYS_MENU_ROOT));
        return resultVO;
    }
}
