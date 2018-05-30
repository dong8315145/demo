package com.example.demo.service;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.View.MemberView;
import com.example.demo.dao.Member;
import com.example.demo.dto.MemberDTO;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
public interface MemberService {

     List<Member> list(MemberView memberView);

     MemberDTO login(MemberDTO memberDTO);

     Boolean add(Member member);

     Member  getMemberById(String id);

     MemberDTO modify(MemberDTO memberDTO);

     Boolean remove(MemberDTO memberDTO);


}
