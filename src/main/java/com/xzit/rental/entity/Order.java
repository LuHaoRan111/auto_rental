package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LuHaoRan
 * @since 2025-07-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("busi_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 关联车辆id
     */
    private Integer autoId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 出租时间
     */
    private LocalDateTime rentalTime;

    /**
     * 出租类型
     */
    private Integer typeId;

    /**
     * 日租金额
     */
    private Integer rent;

    /**
     * 押金
     */
    private Integer deposit;

    /**
     * 起租里程
     */
    private Integer mileage;

    /**
     * 归还时间
     */
    private LocalDateTime returnTime;

    /**
     * 归还里程
     */
    private Integer returnMileage;

    /**
     * 应付租金
     */
    private Integer rentPayable;

    /**
     * 实付租金
     */
    private Integer rentActual;

    /**
     * 押金返还状态 0未返还 1已返还
     */
    private Boolean depositReturn;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Boolean deleted;
}
