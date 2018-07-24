package com.example.demo.dao.mapper;

import com.example.demo.dao.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public interface ProductCategoryMapper {

    int deleteByPrimaryKey(Integer categoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
}