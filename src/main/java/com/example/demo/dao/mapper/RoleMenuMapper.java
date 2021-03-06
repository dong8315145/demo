package com.example.demo.dao.mapper;

import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    int selectCount(@Param("params") Map<String, Object> params)throws DaoException;
}