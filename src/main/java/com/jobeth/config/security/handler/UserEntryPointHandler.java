package com.jobeth.config.security.handler;

import com.jobeth.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.jobeth.vo.ResultVO;
import com.jobeth.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:23 2020/4/10
 */
@Component
public class UserEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录处理
     *
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param e                   e
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.writeResultByResultEnum(httpServletResponse, ResultEnum.USER_NOT_LOGIN);
    }
}
