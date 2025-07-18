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
@TableName("busi_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 客户年龄
     */
    private Integer age;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 身份证号码
     */
    private String idNum;

    /**
     * 客户状态 0黑名单 1白名单
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
