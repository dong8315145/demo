package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-12 14:21
 **/
@Entity
@Data
@DynamicUpdate
public class Member  implements Serializable {

    private String id;

    private String name;

    private String password;

    private Date createTime;

    private Date updateTime;

}
