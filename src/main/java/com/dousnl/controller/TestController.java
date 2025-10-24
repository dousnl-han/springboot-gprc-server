package com.dousnl.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 * 用于接收网关分发的请求
 */
@RestController
@RequestMapping("/user-orch")
public class TestController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot gRPC Server!");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "springboot-grpc-server");
        response.put("path", "/user-orch/hello");
        return response;
    }

    @GetMapping("/profile")
    public Map<String, Object> profile() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User profile endpoint");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "springboot-grpc-server");
        response.put("path", "/user-orch/profile");
        response.put("user", "test-user");
        return response;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "springboot-grpc-server");
        response.put("path", "/user-orch/create");
        response.put("data", data);
        return response;
    }

    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "springboot-grpc-server");
        response.put("path", "/user-orch/status");
        return response;
    }
}

