package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
public interface MemberService {

     MemberDTO login(MemberDTO memberDTO);

     Member findOne(String id);

     MemberDTO modify(Member member);




}
