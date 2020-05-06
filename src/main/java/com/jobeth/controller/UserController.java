package com.jobeth.controller;


import com.jobeth.config.security.config.JwtConfig;
import com.jobeth.enums.ResultEnum;
import com.jobeth.po.User;
import com.jobeth.service.UserService;
import com.jobeth.utils.JsonUtil;
import com.jobeth.utils.JwtUtil;
import com.jobeth.utils.ResultUtil;
import com.jobeth.utils.StringUtils;
import com.jobeth.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author Jobeth
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listUser")
    public String listUser() {
        return "listUser";
    }

    @PostMapping("/getUserInfoByToken")
    public void getUserInfoByToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取请求头中JWT的Token
        String token = request.getHeader(JwtConfig.tokenName);
        log.info("【获取用户信息】-请求token-[{}]", token);
        if (StringUtils.notNullAndEmpty(token)) {
            UserVO userVO = JwtUtil.parseToken(token);
            if (userVO != null) {
                userService.generateUserAllInfo(userVO);
                ResultUtil.writeSuccessResult(response, userVO);
                log.info("【获取用户信息】-userVO-[{}]", JsonUtil.toJsonString(userVO));
            } else {
                ResultUtil.writeResultByResultEnum(response, ResultEnum.INTERNAL_SERVER_ERROR);
            }
        }
        //请求参数错误
        else {
            ResultUtil.writeResultByResultEnum(response, ResultEnum.REQUEST_PARAMETER_ERROR);
        }

    }

    public static void main(String[] args) {
        System.out.println(ResultEnum.REQUEST_PARAMETER_ERROR);
    }
}

