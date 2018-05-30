package com.example.demo.controller;


import com.example.demo.View.BaseView.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-26 14:43
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderMasterController {

    @PostMapping("/list")
    public ResultVO list() {
        return null;
    }
}
