package com.xzit.rental.service;

import com.xzit.rental.entity.User;
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
public interface IUserService extends IService<User> {
    User selectUserByName(String username);

    List<String> selectRoleName(int id);
}
