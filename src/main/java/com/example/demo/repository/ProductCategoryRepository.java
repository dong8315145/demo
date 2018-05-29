package com.example.demo.repository;

import com.example.demo.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface ProductCategoryRepository extends Serializable,
        JpaRepository<ProductCategory,Integer>,
        JpaSpecificationExecutor<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
