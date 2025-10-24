package com.dousnl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot 主启动类
 * 支持gRPC服务和网关注册
 */
@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
        System.out.println("正在启动Spring Boot gRPC服务...");
        SpringApplication.run(Main.class, args);
        System.out.println("Spring Boot gRPC服务启动完成！");
    }
}
