package com.example.demo.service.impl;

import com.example.demo.View.MemberView;
import com.example.demo.common.constant.PermissionConstants;
import com.example.demo.common.exception.DaoException;
import com.example.demo.common.permissionfilter.Permission;
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
    public void login() throws DaoException  {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("admin");
        memberDTO.setPassword("admin");
        memberService.login(memberDTO);
    }

    @Test
    public void findByID()  throws DaoException {
        Member member = memberService.getMemberById("1");
        System.out.println("name:" + member.getName());
    }

    @Test
    public void mapperTest() throws DaoException  {
        Member member = memberService.getMemberById("1");
        System.out.println("name:" + member.getName());
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println("productCategoryName:" + productCategory.getCategoryName());
    }

    @Test
    @Permission(PermissionConstants.DATA_REMOVE)
    public void listTest() throws DaoException {
        MemberView memberVive = new MemberView();
        memberVive.setPageNum(0);
        memberVive.setPageSize(1);
        //memberVive.setName("a");
        List<Member> list=memberService.list(memberVive);
        for(Member member:list){
            System.out.println(member.getName());
        }
        System.out.println("list:"+list.size());
    }
}