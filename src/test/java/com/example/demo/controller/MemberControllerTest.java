package com.example.demo.controller;

import com.example.demo.View.MemberView;
import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.Member;
import com.example.demo.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.example.demo.dao")
public class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @Autowired
    private MemberService memberService;

    @Test
    public void list() {
        MemberView memberView = new MemberView();
        memberView.setPageSize(1);
        memberView.setPageNum(1);
        try {
            memberController.list(memberView);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Cacheable(value = "member", key = "#member.id")
    public Member view(Member member) throws DaoException{
        Member member1 = new Member();
        member1 = memberService.getMemberById(member.getId());
        return member1;
    }

    @Test
    @Cacheable(value = "member", key = "#id")
    public void add() {
    }

    @Test
    public void modify() {
    }

    @Test
    public void remove() {
    }
}