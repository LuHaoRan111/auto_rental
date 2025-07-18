package com.xzit.rental.security;

import com.alibaba.fastjson.JSON;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登录失败
 */
@Component
public class LoginFalseHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int code= ResultCode.ERROR;
        String msg=null;
        if(exception instanceof AccountExpiredException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户过期";
        }else if(exception instanceof BadCredentialsException){
            code=ResultCode.UNAUTHORIZED;
            msg="用户名或密码错误";
        }else if(exception instanceof DisabledException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户已禁用";
        }else if(exception instanceof LockedException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户已锁定";
        }else if(exception instanceof CredentialsExpiredException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "密码已过期";
        }else if(exception instanceof  InternalAuthenticationServiceException){
            code=ResultCode.UNAUTHORIZED;
            msg="用户不存在";
        }else if(exception instanceof AuthenticationException){
            code=ResultCode.UNAUTHORIZED;
            msg=exception.getMessage();
        }
        else{
            msg="登录失败";
        }
        String result = JSON.toJSONString(Result.error().setCode(code).setMessage(msg));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }

}
