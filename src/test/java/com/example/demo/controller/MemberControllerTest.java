package com.example.demo.controller;

import com.example.demo.View.MemberView;
import com.example.demo.common.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.example.demo.dao")
public class MemberControllerTest {

    @Autowired
    private  MemberController memberController;

    @Test
    public void list() {
        MemberView memberView=new MemberView();
        memberView.setPageSize(1);
        memberView.setPageNum(1);
        try {
            memberController.list(memberView);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add() {
    }

    @Test
    public void modify() {
    }

    @Test
    public void remove() {
    }
}