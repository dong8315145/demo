package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Member  implements Serializable {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    private String name;

    private String password;

    private Date createTime;

    private Date updateTime;

}
