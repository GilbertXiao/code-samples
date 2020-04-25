package com.gilxyj.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: implicit
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-25 23:41
 **/
@Controller
public class IndexController {

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
}
