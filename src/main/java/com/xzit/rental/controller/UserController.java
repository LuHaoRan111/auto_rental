package com.xzit.rental.controller;

import com.xzit.rental.entity.User;
import com.xzit.rental.service.IUserService;
import com.xzit.rental.utils.Result;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LuHaoRan
 * @since 2025-07-16
 */
@RestController
@RequestMapping("/rental/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }
}
