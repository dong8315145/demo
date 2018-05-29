package com.example.demo.service;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.dao.Member;
import com.example.demo.dto.MemberDTO;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
public interface MemberService {

     MemberDTO login(MemberDTO memberDTO);

     ResultVO getMemberById(String id);

     ResultVO modify(MemberDTO memberDTO);

     ResultVO remove(MemberDTO memberDTO)


}
