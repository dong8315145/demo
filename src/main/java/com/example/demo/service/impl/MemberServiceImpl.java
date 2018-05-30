package com.example.demo.service.impl;

import com.example.demo.View.MemberView;
import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.ResultException;
import com.example.demo.dao.Member;
import com.example.demo.dao.mapper.MemberMapper;
import com.example.demo.dto.MemberDTO;
import com.example.demo.redis.RedisManager;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private MemberMapper memberMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Member> list(MemberView memberView) {
        PageHelper.startPage(memberView.getPageNum(), memberView.getPageSize(), memberView.getOrderByKey());
        Map<String, Object> params = new HashMap<>();
        params.put("name", memberView.getName());
        List<Member> list = memberMapper.selectByParams(params);
        PageInfo<Member> pageInfo = new PageInfo<Member>(list);
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Boolean add(Member member) {
        memberRepository.save(member);
        return true;
    }

    public Set<Member> allMember() {
        // TODO Auto-generated method stub
        Set<Member> memberSet = redisManager.setMembers(ALL);
        if (null == memberSet || memberSet.isEmpty()) {
            List<Member> townList = memberRepository.findAll();
            memberSet = new HashSet<>(townList.size() * 2);
            for (Member member : townList) {
                memberSet.add(member);
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
    public Member getMemberById(String id) {
        return findIt(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public MemberDTO modify(MemberDTO memberDTO) {
        Member member = findIt(memberDTO.getId());
        BeanUtils.copyProperties(memberDTO, member);
        memberRepository.saveAndFlush(member);
        redisManager.update(ID + member.getId(), member);
        return memberDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Boolean remove(MemberDTO memberDTO) {
        String id = memberDTO.getId();
        Member member = findIt(id);
        if (null == member) {
            member = memberRepository.findById(id).get();
            if (null == member) {
                return false;
            }
        }
        //TODO 逻辑删除改关键字
        memberRepository.saveAndFlush(member);
        redisManager.update(ID + member.getId(), member);
        return true;
    }

    private Member findIt(String id) {
        Member member = (Member) redisManager.get(ID + id);
        if (null == member) {
            member = memberRepository.findById(id).get();
            if (null != member) {
                redisManager.add(ID + id, member);
            }
        }
        return member;
    }

}
