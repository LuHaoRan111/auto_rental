package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@TableName("sys_user")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 账户是否过期
     */
    private boolean isAccountNonExpired=true;

    /**
     * 账户是否被锁定
     */
    private boolean isAccountNonLocked=true;

    /**
     * 密码是否过期
     */
    private boolean isCredentialsNonExpired=true;

    /**
     * 账户是否可用
     */
    private boolean isEnabled=true;

    /**
     * 用户真实姓名
     */
    private String realname;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 所属部门id
     */
    private Integer deptId;

    /**
     * 所属部门名称
     */
    private String deptName;

    /**
     * 性别
     */
    private Boolean gender;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String emial;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否管理员
     */
    private Boolean isAdmin;

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


    @TableField(exist = false)
    //获取权限
    private Collection<? extends GrantedAuthority> authorities;

    @TableField(exist = false)
    private List<Permission> permissionList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", permissionList=" + permissionList +
                ", authorities=" + authorities +
                '}';
    }

}
