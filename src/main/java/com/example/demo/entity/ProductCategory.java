package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory  implements Serializable {
    /**
     * 类目ID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名称
     */

    private String categoryName;


    /**
     * 类目类型
     */
    private Integer categoryType;

    /**
     * 创建时间
     */

    private Date createTime;


    /**
     * 更新时间
     */

    private Date updateTime;


    public ProductCategory() {
    }

}
