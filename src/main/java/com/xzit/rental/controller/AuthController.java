package com.xzit.rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.xzit.rental.entity.Permission;
import com.xzit.rental.entity.User;
import com.xzit.rental.security.CustomerAuthenticationException;
import com.xzit.rental.service.IUserService;
import com.xzit.rental.utils.JwtUtils;
import com.xzit.rental.utils.RedisUtils;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.RouteTreeUtils;
import com.xzit.rental.vo.RouteVO;
import com.xzit.rental.vo.TokenVO;
import com.xzit.rental.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/rental/auth")
public class AuthController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IUserService userService;

    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request, HttpServletResponse response){
        //获取token
        String token=request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            token=request.getParameter("token");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        String newToken="";
        if(StrUtil.equals(username,userDetails.getUsername())){
            Map<String,Object> map = new HashMap<>();
            map.put("username",userDetails.getUsername());
            newToken =JwtUtils.createToken(map);
        }else{
            throw new CustomerAuthenticationException("token异常");
        }
        //获取本次token的过期时间
        NumberWithFormat claim = (NumberWithFormat)JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime=(DateTime)claim.convert(DateTime.class,claim);
        long expireTime=dateTime.getTime();
        TokenVO tokenVo =new TokenVO();
        tokenVo.setToken(newToken).setExpireTime(expireTime);
        //清楚原有token
        redisUtils.delete("token"+token);
        //设置新token
        long nowTime= DateTime.now().getTime();
        String newTokenKey="token"+newToken;
        redisUtils.set(newTokenKey,newToken,expireTime-nowTime/1000);
        return Result.success(tokenVo).setMessage("刷新token成功");
    }


    @GetMapping("/info")
    public Result getUserInfo(){
        //从securityContextHolder上下文中获取认证信息
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return Result.error().setMessage("认证信息为空");
        }
        User user = (User)authentication.getPrincipal();
        List<String> list=userService.selectRoleName(user.getId());
        Object[] array=list.toArray();
        UserInfoVO userInfoVo=new UserInfoVO(user.getId(),
                user.getUsername(), user.getAvatar(),user.getNickname(),array);
        return Result.success(userInfoVo).setMessage("获取用户信息成功");
    }

    @GetMapping("/menuList")
    public Result getMenuList(){
        //获取当前用户信息
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return Result.error().setMessage("认证信息为空");
        }
        User user = (User)authentication.getPrincipal();
        //获取用户信息的权限列表
        List<Permission> permissionList=user.getPermissionList();
        //将permission_type为2的按钮移除,不需要生成对应的菜单
        permissionList.removeIf(permission -> permission == null || Objects.equals(permission.getPermissionType(), 2));
        //将permission_type为1的菜单生成树形结构
        List<RouteVO> routeVOList= RouteTreeUtils.buildRouteTree(permissionList,0);
        return Result.success(routeVOList).setMessage("获取菜单成功");
    }
}
