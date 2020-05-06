package com.jobeth.exception;

import com.jobeth.enums.ResultEnum;

/**
 * 异常类
 *
 * @author Jobeth
 * @since 2020/5/3 22:03
 */
public class CommonException extends RuntimeException {

    private Integer code;
    private String message;

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
