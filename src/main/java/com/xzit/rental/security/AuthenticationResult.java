package com.xzit.rental.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证登录成功处理结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResult {
    private int id;
    private int code;
    private String token;
    private Long expireTime;
}
