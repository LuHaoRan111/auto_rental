package com.xzit.rental.security;


import com.alibaba.fastjson.JSON;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 匿名用户无法访问
 */
@Component
public class CustomerAnonymousEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String result= JSON.toJSONString(Result.error().setCode(ResultCode.UNAUTHORIZED).
                                                        setMessage("匿名用户,拒绝登录"));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
