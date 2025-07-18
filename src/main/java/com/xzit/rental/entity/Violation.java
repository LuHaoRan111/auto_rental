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
@TableName("busi_violation")
public class Violation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 违章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车辆id
     */
    private Integer autoId;

    /**
     * 违章时间
     */
    private LocalDateTime violationTime;

    /**
     * 违章事由
     */
    private String reason;

    /**
     * 违章地点
     */
    private String location;

    /**
     * 扣分
     */
    private Integer daductPoints;

    /**
     * 罚款
     */
    private Integer fine;

    /**
     * 是否处理 0未处理 1已处理
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
     * 是否删除
     */
    private Boolean deleted;
}
