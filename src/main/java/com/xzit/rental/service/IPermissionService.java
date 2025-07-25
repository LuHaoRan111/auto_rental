package com.xzit.rental.service;

import com.xzit.rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LuHaoRan
 * @since 2025-07-16
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> selectPermissionByUserId(Integer userId);
}
