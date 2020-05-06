package com.jobeth.vo;

import lombok.Data;

/**
 * 功能描述
 *
 * @author Jobeth
 * @date Created by IntelliJ IDEA on 12:12 2020/4/10
 */
@Data
public class ResultVO {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;

    private Object data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
