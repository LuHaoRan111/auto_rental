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
@TableName("busi_maintain")
public class Maintain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保养id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车辆id
     */
    private Integer autoId;

    /**
     * 维保时间
     */
    private LocalDate maintainTime;

    /**
     * 维保地点
     */
    private String location;

    /**
     * 维保项目
     */
    private String item;

    /**
     * 维保费用
     */
    private Integer cost;

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
