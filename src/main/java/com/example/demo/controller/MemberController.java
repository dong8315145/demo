package com.example.demo.controller;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.View.MemberView;
import com.example.demo.common.enums.ResultEnum;
import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.impl.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 登录功能，修改密码，登出功能。主要是那些不用token的
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @PostMapping("/list")
    public ResultVO list(MemberView memberView) throws DaoException {
        List<Member> list = memberService.list(memberView);
        return new ResultVO(ResultEnum.SUCCESS, list);
    }

    @PostMapping("/add")
    public ResultVO add(Member member) throws DaoException{
        memberService.add(member);
        return new ResultVO(ResultEnum.SUCCESS);
    }

    @PostMapping("/modify")
    public ResultVO modify(MemberDTO memberDTO) throws DaoException{
        memberService.modify(memberDTO);
        return new ResultVO(ResultEnum.SUCCESS, memberDTO);
    }

    @PostMapping("/remove")
    public ResultVO remove(MemberDTO memberDTO) throws DaoException{
        if (memberService.remove(memberDTO)) {
            return new ResultVO(ResultEnum.SUCCESS, memberDTO);
        } else {
            return new ResultVO(ResultEnum.FAIL);
        }
    }


}
