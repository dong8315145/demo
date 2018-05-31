package com.example.demo.service;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.View.MemberView;
import com.example.demo.common.exception.DaoException;
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

     List<Member> list(MemberView memberView)throws DaoException;

     MemberDTO login(MemberDTO memberDTO)throws DaoException;

     Boolean add(Member member)throws DaoException;

     Member  getMemberById(String id)throws DaoException;

     MemberDTO modify(MemberDTO memberDTO)throws DaoException;

     Boolean remove(MemberDTO memberDTO)throws DaoException;


}
