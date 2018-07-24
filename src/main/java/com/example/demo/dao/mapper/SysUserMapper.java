package com.example.demo.dao.mapper;

import com.example.demo.dao.SysUser;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}