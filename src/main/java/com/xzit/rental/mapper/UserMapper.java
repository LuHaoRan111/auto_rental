package com.xzit.rental.mapper;

import com.xzit.rental.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LuHaoRan
 * @since 2025-07-16
 */
public interface UserMapper extends BaseMapper<User> {

    List<String> selectRolesByUserId(Integer id);
}
