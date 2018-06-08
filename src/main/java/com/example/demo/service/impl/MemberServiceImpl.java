package com.example.demo.service.impl;

import com.example.demo.View.MemberView;
import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.DaoException;
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
    public List<Member> list(MemberView memberView) throws DaoException {

        PageHelper.startPage(memberView.getPageNum(), memberView.getPageSize(), memberView.getOrderByKey());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", memberView.getName());
        System.out.println("name:" + params.get("name"));
        List<Member> list = new ArrayList<>();
        list = memberMapper.selectByParams(params);
        PageInfo<Member> pageInfo = new PageInfo<Member>(list);
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Boolean add(Member member) {
        Member mermeber = memberRepository.save(member);
        redisManager.setAdd(REDIS_PREFIX + ID + member.getId(), member);
        return true;
    }

    public Set<Member> allMember() throws DaoException {
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
    public MemberDTO login(MemberDTO memberDTO) throws DaoException {
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
    public Member getMemberById(String id) throws DaoException {
        return findIt(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public MemberDTO modify(MemberDTO memberDTO) throws DaoException {
        Member member = findIt(memberDTO.getId());
        BeanUtils.copyProperties(memberDTO, member);
        memberRepository.saveAndFlush(member);
        redisManager.update(ID + member.getId(), member);
        return memberDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Boolean remove(MemberDTO memberDTO) throws DaoException {
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


    private Member findIt(String id) throws DaoException {
        Member member = (Member) redisManager.get(REDIS_PREFIX + ID + id);
        if (null == member) {

            member = memberRepository.findById(id).get();
            if (null != member) {
                redisManager.add(ID + id, member);
            }
        }
        return member;
    }

}
