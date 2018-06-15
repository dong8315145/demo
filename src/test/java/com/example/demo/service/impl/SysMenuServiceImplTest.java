package com.example.demo.service.impl;

import com.example.demo.View.SysMenuVO;
import com.example.demo.common.constant.CommonConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceImplTest {

    @Autowired
    SysMenuServiceImpl sysMenuService;

    @Test
    @Transactional
    public void findTree() {
      SysMenuVO sysMenuVO= sysMenuService.findTreeByPid(CommonConstants.SYS_MENU_ROOT);
        System.out.println(">>>>>>>>"+sysMenuVO);
    }
}