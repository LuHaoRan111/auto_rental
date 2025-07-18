package com.xzit.rental.security;

import com.alibaba.fastjson.JSON;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * 权限不足
 */

@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String result=JSON.toJSONString(Result.error().setCode(ResultCode.UNAUTHORIZED).setMessage("权限不足"));
        outputStream.write(result.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
