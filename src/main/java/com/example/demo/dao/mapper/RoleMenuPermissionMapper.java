package com.example.demo.dao.mapper;

import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.RoleMenuPermission;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface RoleMenuPermissionMapper {
    int insert(RoleMenuPermission record);

    int insertSelective(RoleMenuPermission record);

    int selectCount(@Param("params") Map<String, Object> params)throws DaoException;

}