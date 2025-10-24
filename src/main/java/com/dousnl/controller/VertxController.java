package com.dousnl.controller;


import com.dousnl.config.GatewayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VertxController {

    @Autowired
    private GatewayConfig gatewayConfig;

    @RequestMapping("/hello1")
    public String hello(){
        System.out.println("测试"+gatewayConfig.getDescription());
        return "test ccc";
    }
}
