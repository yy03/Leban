package com.yy.yeb.controller;

/*
       测试
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello1() {
        return "/employee/basic/**";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello2() {
        return "/employee/advanced/**";
    }
}
