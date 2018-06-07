package com.example.demo.service.impl;

import com.example.demo.common.enums.ProductInfoStatusEnum;
import com.example.demo.dao.ProductInfo;
import com.example.demo.repository.ProductInfoRepository;
import com.example.demo.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;


    @Override
    public ProductInfo findOne(String productInfoId) {
        return repository.getOne(productInfoId);
    }


    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductInfoStatusEnum.UP);
    }

    @Override
    public List<ProductInfo> findDownAll() {
        return repository.findByProductStatus(ProductInfoStatusEnum.DOWN);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
