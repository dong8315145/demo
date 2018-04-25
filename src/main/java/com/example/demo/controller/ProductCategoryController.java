package com.example.demo.controller;

import com.example.demo.View.BaseView.BaseVO;
import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductInfo;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;


    @PostMapping("/list")
    public BaseVO list() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        List<Integer> productcategoryTypeList=new ArrayList<>();
//        List<Integer> productcategoryTypeList =
//                productInfoList.stream().distinct().
//                        map(e -> e.getCategoryType()).
//                        collect(Collectors.toList());
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(productcategoryTypeList);

        return ResultVO.success();
    }

}
