package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-27 09:35
 **/
@Entity
@Data
@DynamicUpdate
public class SellerInfo implements Serializable {

    @Id
    private String Id;
    private String userName;
    private String passWord;
    private String openId;
    private Date createTime;
    private Date updateTime;


}
