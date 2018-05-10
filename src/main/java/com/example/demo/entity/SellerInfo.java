package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-27 09:35
 **/
@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;


}
