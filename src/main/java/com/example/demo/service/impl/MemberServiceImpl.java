package com.example.demo.service.impl;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.ResultException;
import com.example.demo.dao.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:42
 **/
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private static final String REDIS_PREFIX = "demo:member:";
    private static final String ID = REDIS_PREFIX + "id:";
    private static final String ALL = REDIS_PREFIX + "all:";
    @Autowired
    private RedisManager<String, Member> redisManager;

    @Autowired
    private MemberRepository memberRepository;

    public Set<Member> allMember() {
        // TODO Auto-generated method stub
        Set<Member> memberSet = redisManager.setMembers(ALL);
        if (null == memberSet || memberSet.isEmpty()) {
            List<Member> townList = memberRepository.findAll();
            memberSet = new HashSet<>(townList.size() * 2);
            for (Member member : townList) {
                areaSet.add(member);
                redisManager.update(ID + member.getId(), member);
                redisManager.setAdd(ALL, member);
            }
        }
        return memberSet;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResultVO getMemberById(String id) {
        Member member=findIt(id);
        if(member==null){
            return new ResultVO(ResultEnum.FAIL,"用户无效");
        }
         return new ResultVO(ResultEnum.SUCCESS,member);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResultVO modify(MemberDTO memberDTO) {
       Member member=findIt(memberDTO.getId());
       BeanUtils.copyProperties(memberDTO,member);
       memberRepository.saveAndFlush(member);
       redisManager.update(ID+member.getId(),member);
       return new ResultVO(ResultEnum.SUCCESS,memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResultVO remove(MemberDTO memberDTO) {
        Member member=findIt(memberDTO.getId());
        if (null == member) {
            member= optionalMember=memberRepository.findById(id).get();
            if (null == member) {
              return new ResultVO(ResultEnum.FAIL,"无效用户");
            }
        }
        //TODO 逻辑删除改关键字
        memberRepository.saveAndFlush(member);
        redisManager.update(ID+member.getId(),member);
        return new ResultVO(ResultEnum.SUCCESS);
    }

    private Member findIt(String id){
        Member member = (Member) redisManager.get(ID + id);
        if (null == member) {
            member= optionalMember=memberRepository.findById(id).get();
            if (null != member) {
                redisManager.add(ID + id, area);
            }
        }
        return member;
    }

}
