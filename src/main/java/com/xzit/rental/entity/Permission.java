package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionLabel;

    /**
     * 父权限id
     */
    private Integer pid;

    /**
     * 父权限名称
     */
    private String parentLabel;

    /**
     * 权限标识
     */
    private String permissionCode;

    /**
     * 权限路由地址
     */
    private String routePath;

    /**
     * 权限路由名称
     */
    private String routeName;

    /**
     * 权限路径
     */
    private String routeUrl;

    /**
     * 权限类型
     */
    private Integer permissionType;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Boolean deleted;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)  //在序列化的时候，为空的属性不序列化
    // 子权限
    private List<Permission> children;
}
