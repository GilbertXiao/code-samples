package com.gilxyj.userserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: authorization_code
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-20 01:05
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin(){
        return "Hello Admin";
    }
}
