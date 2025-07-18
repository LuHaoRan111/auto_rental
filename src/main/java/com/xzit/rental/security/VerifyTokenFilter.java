package com.xzit.rental.security;



import cn.hutool.core.util.StrUtil;
import com.xzit.rental.utils.JwtUtils;
import com.xzit.rental.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OncePerRequestFilter是springboot提供的过滤器抽象类
 * 在spring security应用广泛，可以用来过滤请求
 * 这个过滤器通常被用于继承实现并在每次请求时执行
 */
@Component
public class VerifyTokenFilter extends OncePerRequestFilter {
    @Value("${request.login-url}")
    private String loginUrl;



    @Autowired
    private RedisUtils redisUtils;


    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    private LoginFalseHandler loginFalseHandler;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String url=request.getRequestURI();
        if(!StrUtil.equals(url,loginUrl)){
            //校验token
            try{
                validateToken(request,response);
            }
            catch (AuthenticationException e){
                loginFalseHandler.onAuthenticationFailure(request,response,e);
            }
        }
        doFilter(request,response,filterChain);
    }



    private void validateToken(HttpServletRequest request,HttpServletResponse response){
        String token=request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            token=request.getParameter("token");
        }
        if(StrUtil.isEmpty(token)){
            throw new CustomerAuthenticationException("token为空");
        }
        //如果token存在，需要和redis中存的token去比较
        String tokenKey="token"+token;
        String redisToken=redisUtils.get(tokenKey);
        if(StrUtil.isEmpty(redisToken)){
            throw new CustomerAuthenticationException("token已过期");
        }
        //如果redis中存在token，需要校验token是否合法
        if (!StrUtil.equals(token,redisToken)){
            throw new CustomerAuthenticationException("token错误");
        }
        String name=JwtUtils.parseToken(token).getClaim("username").toString();
        if(StrUtil.isEmpty(name)){
            throw new CustomerAuthenticationException("token解析失败");
        }
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(name);
        if(userDetails==null){
            throw new CustomerAuthenticationException("用户不存在");
        }
        //创建并设置认证信息
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
