package com.xzit.rental.utils;


import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    //jwt的密钥
    public static final String SECRET_KEY="LuHaoRan";

    //jwt的过期时间
    public static final long EXPIRE_TIME=1000L*60*30;

    public static String createToken(Map<String,Object> payload){
        DateTime now= DateTime.now();
        DateTime newTime=new DateTime(now.getTime()+EXPIRE_TIME);
        //设置签发时间
        payload.put(JWTPayload.ISSUED_AT,now);
        //设置过期时间
        payload.put(JWTPayload.EXPIRES_AT,newTime);
        //设置生效时间，确保token在签发之后立即生效
        payload.put(JWTPayload.NOT_BEFORE,now);

        return JWTUtil.createToken(payload,SECRET_KEY.getBytes());
    }

    public static JWTPayload parseToken(String token){
        JWT jwt = JWTUtil.parseToken(token);
        if(!jwt.setKey(SECRET_KEY.getBytes()).verify()){
            throw new RuntimeException("token异常");
        }
        if(!jwt.validate(0)){
            throw new RuntimeException("token已过期");
        }

        return jwt.getPayload();
    }

    public static void main(String[] args) {
        /**
         Map<String,Object> payload= new HashMap<>();
         payload.put("username","admin");
         payload.put("id","111");
         String token=createToken(payload);
         System.out.println(token);
        */
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3NTI3MTY1MzcsImlkIjoiMTExIiwiZXhwIjoxNzUyNzE2NTM3LCJpYXQiOjE3NTI3MTY1MzcsInVzZXJuYW1lIjoiYWRtaW4ifQ.t9fY9Hfo6vK5ewCFtSt84KvwGgv_j-vuUX2IsY18fPI";
        JWTPayload payload=parseToken(token);
        System.out.println(payload.getClaim("username"));
    }
}
