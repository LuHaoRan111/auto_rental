package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String daptName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 上级部门id
     */
    private Integer pid;

    /**
     * 上级部门名称
     */
    private String parentName;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 是否删除
     */
    private Boolean deleted;
}
