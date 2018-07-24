package com.example.demo.dao.mapper;

import com.example.demo.dao.MenuPermission;
import org.springframework.stereotype.Component;

@Component
public interface MenuPermissionMapper {
    int insert(MenuPermission record);

    int insertSelective(MenuPermission record);
}