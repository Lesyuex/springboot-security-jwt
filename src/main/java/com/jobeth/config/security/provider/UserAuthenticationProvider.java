package com.jobeth.config.security.provider;

import com.jobeth.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.jobeth.dto.UserDTO;

/**
 * description 自定义登录验证
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 13:25 2020/4/10
 */
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public UserAuthenticationProvider(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param authentication authentication
     * @return Authentication
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        // 获取表单输入中返回的用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        log.info("【登录信息-username:{},password:{}】", username, password);
        // 查询用户是否存在
        UserDTO userDTO = (UserDTO) userDetailsService.loadUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //判断密码
        if (!new BCryptPasswordEncoder().matches(password, userDTO.getPassword())) {
            throw new BadCredentialsException("用户名和密码匹配不正确");
        }
        if (!userDTO.getEnabled()) {
            throw new LockedException("用户已冻结");
        }
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userDTO, password, userDTO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
