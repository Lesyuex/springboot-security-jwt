package com.jobeth.config.security.config;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 11:42 2020/4/10
 */
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    /**
     * 密钥KEY
     */
    public static String secret;
    /**
     * TokenKey
     */
    public static String tokenName;
    /**
     * Token前缀字符
     */
    public static String tokenPrefix;
    /**
     * 过期时间
     */
    public static Integer expiration;
    /**
     * 不需要认证的接口
     */
    public static String antMatchers;

    public JwtConfig() {
    }

    public void setSecret(String secret) {
        JwtConfig.secret = secret;
    }

    public void setTokenName(String tokenName) {
        JwtConfig.tokenName = tokenName;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JwtConfig.tokenPrefix = tokenPrefix;
    }

    public void setExpiration(Integer expiration) {
        JwtConfig.expiration = expiration;
    }

    public void setAntMatchers(String antMatchers) {
        JwtConfig.antMatchers = antMatchers;
    }
}
