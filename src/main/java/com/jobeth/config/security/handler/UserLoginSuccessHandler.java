package com.jobeth.config.security.handler;

import com.jobeth.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.jobeth.dto.UserDTO;
import com.jobeth.utils.JwtUtil;
import com.jobeth.utils.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description 登录成功处理类
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:39 2020/4/10
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param authentication      authentication
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        UserDTO user = (UserDTO) authentication.getPrincipal();
        String token = JwtUtil.generateToken(user);
        // 封装返回参数
        Map<String, Object> resultData = new HashMap<>(3);
        resultData.put("code", 200);
        resultData.put("msg", "登录成功");
        resultData.put("token", token);
        ResultUtil.responseObjectJson(httpServletResponse, resultData);
    }
}
