package com.example.demo.service.impl;

import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.ResultException;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public ModelAndView login(MemberDTO memberDTO) {
        Member member = memberRepository.findbyNameAndPassword(memberDTO.getName(), memberDTO.getPassword());
        if (member != null) {
        /*
        TODO redis set map,
         */
            return new ModelAndView("index");
        } else {
            new ResultException(ResultEnum.FAIL);
            return null;
        }
    }
}
