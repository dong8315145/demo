package com.example.demo.service;

import com.example.demo.dao.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer productCategoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
