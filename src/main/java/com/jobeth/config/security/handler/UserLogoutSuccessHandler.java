package com.jobeth.config.security.handler;

import com.jobeth.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.jobeth.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description 用户登出类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:48 2020/4/10
 */
@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     * 用户登出,这里应该让前端清除掉Token
     *
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param authentication      authentication
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("【用户登出-执行】");
        SecurityContextHolder.clearContext();
        ResultUtil.writeResultByResultEnum(httpServletResponse, ResultEnum.SERVER_NO_ERROR);
    }
}
