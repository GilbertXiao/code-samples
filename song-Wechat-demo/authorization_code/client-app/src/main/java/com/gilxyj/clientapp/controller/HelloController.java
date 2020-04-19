package com.gilxyj.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @program: authorization_code
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-20 01:16
 **/
@Controller
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/index.html")
    public String hello(String code, Model model){
        if (code != null) {
            MultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id","song");
            map.add("client_secret","123");
            map.add("redirect_uri", "http://localhost:8082/index.html");
            map.add("grant_type", "authorization_code");
            Map<String,String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
            String access_token = resp.get("access_token");
            System.out.println(access_token);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer "+access_token);
            HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
            ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/admin/hello", HttpMethod.GET, httpEntity, String.class);
            model.addAttribute("msg", exchange.getBody());
        }
        return "index";
    }
}
