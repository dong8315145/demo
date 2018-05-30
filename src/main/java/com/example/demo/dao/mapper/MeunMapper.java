package com.example.demo.dao.mapper;

import com.example.demo.dao.Meun;
import org.springframework.stereotype.Component;

@Component
public interface MeunMapper {
    int deleteByPrimaryKey(String id);

    int insert(Meun record);

    int insertSelective(Meun record);

    Meun selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meun record);

    int updateByPrimaryKey(Meun record);
}