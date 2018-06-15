package com.example.demo.service.impl;

import com.example.demo.View.SysMenuVO;
import com.example.demo.common.constant.CommonConstants;
import com.example.demo.dao.SysMenu;
import com.example.demo.repository.RoleMenuRepository;
import com.example.demo.repository.SysMenuRepository;
import com.example.demo.service.SysMenuService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuRepository sysMenuRepository;
    @Autowired
    RoleMenuRepository roleMenuRepository;

    @Override
    public SysMenuVO findBySysRoleID(String roleId){
       List<SysMenu> sysMenuList=roleMenuRepository.findAllByRoleId(roleId);


         return new SysMenuVO();
     }

    @Override
    public SysMenuVO findTreeByPid(String pid) {
        SysMenuVO sysMenuVO=new SysMenuVO();
        BeanUtils.copyProperties(sysMenuRepository.getOne(pid),sysMenuVO);
       List<SysMenu> sysMenuList=sysMenuRepository.findAllByPidAndFlag(pid,CommonConstants.VALID);
       int len=sysMenuList.size();
       if(len>0){
           SysMenuVO[] sysMenuVOS= new SysMenuVO[len];
           for( int  i=0;i<len;i++){
               sysMenuVOS[i]=findTreeByPid(sysMenuList.get(i).getId());
           }
           sysMenuVO.setChildrens(sysMenuVOS);
       }

        return sysMenuVO;
    }
}


