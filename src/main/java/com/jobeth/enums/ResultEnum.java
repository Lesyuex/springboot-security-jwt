package com.jobeth.enums;

import lombok.Getter;

/**
 * 异常枚举类
 *
 * @author JyrpoKoo
 * @version [版本号 2019/12/28]
 * @date Created by IntelliJ IDEA on 23:50 2019/12/28
 */
@Getter
public enum ResultEnum {
    /**
     * 正常
     */
    SERVER_NO_ERROR(200, "操作正常"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    /**
     * 请求参数错误
     */
    REQUEST_PARAMETER_ERROR(501, "请求参数错误"),

    /**
     * Token过期或者无效
     */
    USER_TOKEN_INVALID(502, "用户Token过期或无效"),
    /**
     * Token过期或者无效
     */
    REQUEST_NO_TOKEN(503, "用户Token过期或无效"),
    /**
     * 未登录
     */
    USER_NOT_LOGIN(504, "未登录"),
    /**
     * 登录失败
     */
    USER_LOGIN_FAIL(505, "登录失败，检查登录信息"),
    /**
     * 没有权限
     */
    USER_ACCESS_DENIED(506, "无权限访问"),
    /**
     * 未知
     */
    UNKNOWN(999, "未知");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
