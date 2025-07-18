package com.xzit.rental.security;


import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;


/**
 * 强散列哈希加密
 */
@Configuration
@Data
public class PasswordConfig {
    @Value("${encoder.ctype.strength}")
    private int strength;
    
    @Value("${encoder.ctype.secret}")
    private String secret;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        SecureRandom random = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength, random);
    }

}
