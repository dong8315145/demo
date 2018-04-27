package com.example.demo.service.impl;

import com.example.demo.common.enums.ExceptionEnum;
import com.example.demo.common.enums.PayStatusEnum;
import com.example.demo.common.exception.BaseException;
import com.example.demo.dto.OrderMasterDTO;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.ProductInfo;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderMasterService;
import com.example.demo.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-26 14:32
 **/
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        //查商品
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail od : orderMasterDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(od.getProductId());
            if (productInfo == null) {
                new BaseException(ExceptionEnum.NOT);
            }
            orderAmount = od.getProductPice()
                    .multiply(new BigDecimal(od.getProductQuantity())).add(orderAmount);
        }
        return null;
    }

    @Override
    public OrderMasterDTO findOne(String orderMasterId) {
        return null;
    }

    @Override
    public OrderMasterDTO findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDTO updateOne(String orderMasterId, PayStatusEnum payStatusEnum) {
        return null;
    }
}
