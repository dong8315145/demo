package com.example.demo.repository;

import com.example.demo.common.enums.ProductInfoStatusEnum;
import com.example.demo.dao.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(ProductInfoStatusEnum productInfoStatusEnum);

}
