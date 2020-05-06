package com.jobeth.utils;

import com.jobeth.po.User;
import com.jobeth.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import com.jobeth.config.security.config.JwtConfig;
import com.jobeth.dto.UserDTO;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 11:29 2020/4/10
 */
@Slf4j
public class JwtUtil {
    public JwtUtil() {
    }

    public static String generateToken(UserDTO userDTO) {
        String token = null;
        //登录成功生成生成Token
        try {
            log.info("【登录成功，生成Token的用户信息】-{}", JsonUtil.toJsonString(userDTO));
            token = Jwts.builder()
                    // 放入用户名和用户ID
                    .setId(userDTO.getId() + "")
                    .setSubject(userDTO.getUsername() + "")
                    //签发时间
                    .setIssuedAt(new Date())
                    //签发者
                    .setIssuer("sys")
                    // 自定义属性 放入用户拥有权限
                    .claim("authorities", JsonUtil.toJsonString(userDTO.getAuthorities()))
                    // 失效时间
                    .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration))
                    // 签名算法和密钥
                    .signWith(SignatureAlgorithm.HS512, JwtConfig.secret)
                    .compact();
        } catch (Exception e) {
            log.error("【Token生成失败，发生错误】-{}", e.getMessage());
        }
        return token;
    }

    public static UserVO parseToken(String token) {
        // 解析JWT
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConfig.secret)
                    .parseClaimsJws(token)
                    .getBody();
            String userId = claims.getId();
            String username = claims.getSubject();
            UserVO user = new UserVO();
            user.setId(Long.parseLong(userId));
            user.setUsername(username);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
