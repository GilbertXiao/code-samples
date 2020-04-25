package com.gilxyj.userserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: authorization_code
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-20 01:05
 */
@RestController
@CrossOrigin(value = "*")//因为简化模式没有服务端，我们只能通过 js 来请求资源服务器上的数据，所以资源服务器需要支持跨域
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello Admin";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
