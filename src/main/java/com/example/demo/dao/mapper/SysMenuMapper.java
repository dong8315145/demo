package com.example.demo.dao.mapper;

import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    int selectCount(@Param("params") Map<String, Object> params)throws DaoException;
}