package com.jobeth.config.security.handler;

import com.jobeth.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import com.jobeth.vo.ResultVO;
import com.jobeth.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description 登录失败处理类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:32 2020/4/10
 */
@Slf4j
@Component
public class UserLoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 登录失败处理
     *
     * @param httpServletRequest httpServletRequest
     * @param response           httpServletResponse
     * @param exception          AuthenticationException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】-{}" , exception.getMessage());
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】-{}" , exception.getMessage());
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】-{}" , exception.getMessage());
        }
        ResultUtil.writeResultByResultEnum(response, ResultEnum.USER_LOGIN_FAIL);
    }
}
