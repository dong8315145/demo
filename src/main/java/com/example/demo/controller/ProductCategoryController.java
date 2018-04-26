package com.example.demo.controller;

import com.example.demo.View.BaseView.BaseVO;
import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.View.ProductCategoryVO;
import com.example.demo.View.ProductInfoVO;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductInfo;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
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
        List<Integer> productcategoryTypeList =
                productInfoList.stream().distinct().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(productcategoryTypeList);
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : list) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryName(productCategory.getCategoryName());
            productCategoryVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                Integer categoryId = productCategory.getCategoryId();
                if (categoryId.equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productCategoryVO.setProductInfoVOList(productInfoVOList);
        }
        return ResultVO.success(productCategoryVOList);
    }

}
