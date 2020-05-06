package com.jobeth.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 *
 * @author Jobeth
 * @since 2020/4/30 16:12
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /***
     * 404处理
     * @param e e
     * @return Object
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object notFountHandler(HttpServletRequest request, NoHandlerFoundException e) {
        Map<String, Object> data = new HashMap<>(4);
        data.put("url", request.getRequestURI());
        data.put("method", request.getMethod());
        data.put("message", "请求错误");
        return data;
    }
}