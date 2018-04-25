package com.example.demo.service.impl;

import com.example.demo.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    @Transactional
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("1");
        System.out.println(productInfo.toString());
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void findDownAll() {
    }

    @Test
    @Transactional
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> list = productInfoService.findAll(pageRequest);
        list.forEach(productInfo -> {System.out.println(productInfo.toString());});
        System.out.println(list.getTotalElements());
    }

    @Test
    public void save() {
    }
}
