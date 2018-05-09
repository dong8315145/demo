package com.example.demo.entity;

import com.example.demo.common.enums.OrderStatusEnum;
import com.example.demo.common.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-26 11:24
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     *
     */
    @Id
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家openId(微信）
     */
    private String buyerOpenid;
    /**
     * 总价
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态（0，新建 ，1，完成，2，取消）
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 订单支付状态
     */
    private Integer payStatus = PayStatusEnum.NOTPAYMENT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
