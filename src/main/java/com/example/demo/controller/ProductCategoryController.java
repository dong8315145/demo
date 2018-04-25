package com.example.demo.controller;

import com.example.demo.View.BaseView.ResultVO;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;


    public ResultVO list(){
        return null;
    }

}
