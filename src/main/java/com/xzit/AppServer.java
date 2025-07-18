package com.xzit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */


@SpringBootApplication
@MapperScan("com.xzit.rental.mapper")
public class AppServer
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppServer.class, args);
    }
}
