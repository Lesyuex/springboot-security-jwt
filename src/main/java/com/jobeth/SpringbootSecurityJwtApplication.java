package com.jobeth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:12 2020/4/10
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.jobeth.mapper"})
public class SpringbootSecurityJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityJwtApplication.class, args);
    }
}
