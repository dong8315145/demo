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
public class SellInfo {

    @Id
    private String Id;
    private String userName;
    private String passWord;
    private String openId;
    private Date createTime;
    private Date updateTime;


}
