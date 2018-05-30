package com.example.demo.dao.mapper;

import com.example.demo.dao.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface MemberMapper {

    /**
     * 条件查询
     * @param params
     * @return
     */
    List<Member> selectByParams(@Param("params") Map<String, Object> params);

    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}