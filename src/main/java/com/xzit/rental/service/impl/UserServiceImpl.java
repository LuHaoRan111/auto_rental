package com.xzit.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzit.rental.entity.User;
import com.xzit.rental.mapper.UserMapper;
import com.xzit.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LuHaoRan
 * @since 2025-07-16
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User selectUserByName(String username) {
        QueryWrapper<User> querywrapper=new QueryWrapper<>();
        querywrapper.eq("username",username);
        return baseMapper.selectOne(querywrapper);
    }

    @Override
    public List<String> selectRoleName(int id) {
        return baseMapper.selectRolesByUserId(id);
    }
}
