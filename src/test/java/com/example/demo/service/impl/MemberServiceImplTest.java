package com.example.demo.service.impl;

import com.example.demo.View.MemberView;
import com.example.demo.dao.Member;
import com.example.demo.dao.ProductCategory;
import com.example.demo.dao.mapper.MemberMapper;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.awt.*;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.example.demo.dao")
public class MemberServiceImplTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void login() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("admin");
        memberDTO.setPassword("admin");
        memberService.login(memberDTO);
    }

    @Test
    public void findByID() {
        Member member = memberService.getMemberById("1");
        System.out.println("name:" + member.getName());
    }

    @Test
    public void mapperTest() {
        Member member = memberMapper.selectByPrimaryKey("1");
        System.out.println("name:" + member.getName());
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println("productCategoryName:" + productCategory.getCategoryName());
    }

    @Test
    public void listTest() {
        MemberView memberVive = new MemberView();
        memberVive.setPageNum(1);
        memberVive.setPageSize(1);
        List<Member> list=memberService.list(memberVive);
        System.out.println("list:"+list.size());
    }
}