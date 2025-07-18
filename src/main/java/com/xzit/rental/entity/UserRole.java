package com.xzit.rental.entity;

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
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("user_id")
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;
}
