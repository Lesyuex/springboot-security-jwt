package com.jobeth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc
 *
 * @author Jobeth
 * @since 2020/4/30 15:51
 */
@RestController
@RequestMapping("/index")
public class TestController {
    @RequestMapping("/get")
    public String get() {
        return "get";
    }

    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    @RequestMapping("/put")
    public String put() {
        return "put";
    }

    @RequestMapping("/delete")
    public String delete() {
        return "delete";
    }
}
