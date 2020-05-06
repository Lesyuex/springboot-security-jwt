package com.jobeth.config.security.filter;

import com.jobeth.config.security.config.JwtConfig;
import com.jobeth.enums.ResultEnum;
import com.jobeth.utils.ResultUtil;
import com.jobeth.utils.StringUtils;
import com.jobeth.dto.UserDTO;
import com.jobeth.utils.JsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description 描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 15:41 2020/4/10
 */
@Slf4j
public class JwtTokenFilter extends BasicAuthenticationFilter {
    public JwtTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中JWT的Token
        String token = request.getHeader(JwtConfig.tokenName);
        log.info("【JwtTokenFilter拦截】-请求URL-[{}]-携带SYS-TOKEN：{}", request.getRequestURI(), token);
        if (token == null) {
            ResultUtil.writeResultByResultEnum(response, ResultEnum.REQUEST_NO_TOKEN);
            log.info("【JwtTokenFilter拦截】-请求URL-[{}]-无Token", request.getRequestURI());
        } else {
            try {
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                String userId = claims.getId();
                String username = claims.getSubject();
                if (StringUtils.notNullAndEmpty(username) && StringUtils.notNullAndEmpty(userId)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String roleList = claims.get("authorities").toString();

                    if (StringUtils.notNullAndEmpty(roleList)) {
                        List<Map<String, String>> authorityMap = JsonUtil.parseObject(roleList, List.class);
                        for (Map<String, String> role : authorityMap) {
                            if (StringUtils.notNullAndEmpty(role.get("role"))) {
                                authorities.add(new SimpleGrantedAuthority(role.get("role")));
                            }
                        }
                    }
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(Long.parseLong(userId));
                    userDTO.setUsername(username);
                    userDTO.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDTO, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("【JwtTokenFilter拦截-生成凭证】-authentication:{}", JsonUtil.toJsonString(userDTO));
                    //放行
                    chain.doFilter(request, response);
                }
            } catch (JwtException e) {
                ResultUtil.writeResultByResultEnum(response, ResultEnum.USER_TOKEN_INVALID);
                log.info("【JwtTokenFilter拦截】-Token过期或无效");
            }
        }
    }
}
