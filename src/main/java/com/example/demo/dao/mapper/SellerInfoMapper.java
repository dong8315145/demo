package com.example.demo.dao.mapper;

import com.example.demo.dao.SellerInfo;
import org.springframework.stereotype.Component;

@Component
public interface SellerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);
}