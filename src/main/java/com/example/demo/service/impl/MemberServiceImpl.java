package com.example.demo.service.impl;

import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.ResultException;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO login(MemberDTO memberDTO) {
        Member member = memberRepository.findMemberByNameAndPassword(memberDTO.getName(), memberDTO.getPassword());
        if (member != null) {
        /*
        TODO redis set map,
         */
            return memberDTO;
        } else {
            new ResultException(ResultEnum.FAIL);
            return null;
        }
    }

    @Override
    public Member findOne(String id) {
        Optional <Member> optionalMember=memberRepository.findById(id);
        return optionalMember.get();
    }

    @Override
    public MemberDTO modify(Member member) {
      //  member= memberRepository.findById(member.getId());
        MemberDTO memberDTO= new MemberDTO();
        BeanUtils.copyProperties(memberDTO,member);
        return memberDTO;
    }
}
