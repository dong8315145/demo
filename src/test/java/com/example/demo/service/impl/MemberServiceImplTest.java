package com.example.demo.service.impl;

import com.example.demo.dao.Member;
import com.example.demo.dao.ProductCategory;
import com.example.demo.dao.mapper.MemberMapper;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;


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
        Member member =memberService.findOne("1");
        System.out.println("name:" + member.getName());
    }

    @Test
    public void mapperTest() {
        Member member = memberMapper.selectByPrimaryKey("1");
        System.out.println("name:" + member.getName());
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println("productCategoryName:" + productCategory.getCategoryName());
    }
}