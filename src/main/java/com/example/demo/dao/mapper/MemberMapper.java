package com.example.demo.dao.mapper;

import com.example.demo.common.exception.DaoException;
import com.example.demo.dao.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MemberMapper {

    /**
     * 条件查询
     * @param params
     * @return
     */
    <ModelType> List<ModelType> selectByParams(@Param("params") Map<String, Object> params) throws DaoException;

    int deleteByPrimaryKey(String id) throws DaoException;

    int insert(Member record) throws DaoException;

    int insertSelective(Member record) throws DaoException;

    Member selectByPrimaryKey(String id) throws DaoException;

    int updateByPrimaryKeySelective(Member record) throws DaoException;

    int updateByPrimaryKey(Member record) throws DaoException;
}