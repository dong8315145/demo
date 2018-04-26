package com.example.demo.controller;

import com.example.demo.View.BaseView.BaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-26 14:43
 **/
@RestController
@RequestMapping("/order")
public class OrderMasterController {

    @PostMapping("/list")
    public BaseVO list() {
        return null;
    }
}
