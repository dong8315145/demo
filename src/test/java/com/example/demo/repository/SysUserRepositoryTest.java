package com.example.demo.repository;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.dao.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRepositoryTest {

    @Autowired
    SysUserRepository sysUserRepository;

    @Test
    @Transactional
    public void test1() {
        SysUser sysUser = new SysUser();
        sysUser.setFlag(CommonConstants.VALID);
        sysUser.setName("admin");
        sysUser.setPassword("123456");
        SysUser i = sysUserRepository.findByNameAndPasswordAndFlag("admin","123456","1");
        System.out.println(((SysUser) i).getFlag()+"");
    }

}