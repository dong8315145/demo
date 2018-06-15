package com.example.demo.service;

import com.example.demo.View.SysMenuVO;

public interface SysMenuService {


    SysMenuVO findBySysRoleID(String roleId);

    SysMenuVO findTreeByPid(String pid);
}
