package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-26 17:58
 **/
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPice;

    private Integer productQuantity;

    private String productIocn;

    private Date createTime;

    private Date updateTime;


}
