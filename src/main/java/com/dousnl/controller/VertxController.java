package com.dousnl.controller;


import com.dousnl.config.GatewayConfig;
import com.dousnl.model.HelloResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VertxController {

    @Autowired
    private GatewayConfig gatewayConfig;

    @GetMapping("/hello1")
    public String hello(){
        System.out.println("测试"+gatewayConfig.getDescription());
        return "test ccc";
    }

    @PostMapping("/hello2")
    public HelloResp hello2(@RequestBody HelloResp helloResp){
        System.out.println("测试"+gatewayConfig.getDescription());
        helloResp.setName("hello2");
        helloResp.setCode(200);
        helloResp.setAge(18);
        return helloResp;
    }
}
