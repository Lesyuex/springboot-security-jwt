package com.jobeth.config.security.handler;

import com.jobeth.enums.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.jobeth.vo.ResultVO;
import com.jobeth.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户无权限处理类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:07 2020/4/10
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 无权处理
     *
     * @param request  request
     * @param response response
     * @param e        e
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResultUtil.writeResultByResultEnum(response, ResultEnum.USER_ACCESS_DENIED);
    }
}
