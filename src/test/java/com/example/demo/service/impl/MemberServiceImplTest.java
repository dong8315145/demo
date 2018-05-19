package com.example.demo.service.impl;

import com.example.demo.dto.MemberDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    public void login(){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setName("admin");
        memberDTO.setPassword("admin");
      memberService.login(memberDTO);
    }

}