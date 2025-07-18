package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("auto_info")
public class AutoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌号码
     */
    private String autoNum;

    /**
     * 厂商id
     */
    private Integer makerId;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 车辆类型 0燃油车 1电动车 2混动车
     */
    private Boolean infoType;

    /**
     * 车辆颜色
     */
    private String color;

    /**
     * 汽车排量
     */
    private Double displacement;

    /**
     * 排量计量单位
     */
    private String unit;

    /**
     * 行驶里程
     */
    private Integer mileage;

    /**
     * 日租金额
     */
    private Integer rent;

    /**
     * 上牌日期
     */
    private LocalDate registrationDate;

    /**
     * 车辆图片
     */
    private String pic;

    /**
     * 押金
     */
    private Integer deposit;

    /**
     * 状态 0未租 1已租 2维保 3自用
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 应保次数
     */
    private Integer expectedNum;

    /**
     * 实保次数
     */
    private Integer actualNum;

    /**
     * 是否删除
     */
    private Boolean deleted;
}
