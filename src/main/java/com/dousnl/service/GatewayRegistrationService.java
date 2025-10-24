package com.dousnl.service;

import com.dousnl.config.GatewayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关注册服务
 */
@Service
public class GatewayRegistrationService {
    
    private static final Logger logger = LoggerFactory.getLogger(GatewayRegistrationService.class);
    
    @Autowired
    private GatewayConfig gatewayConfig;
    
    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * 应用启动完成后注册到网关
     */
    @EventListener(ApplicationReadyEvent.class)
    public void registerToGateway() {
        try {
            logger.info("开始注册服务到网关...");
            
            // 使用配置中的IP地址，如果没有配置则获取本机IP
            String localIp = gatewayConfig.getIp();
            if (localIp == null || localIp.isEmpty()) {
                localIp = getLocalIpAddress();
                gatewayConfig.setIp(localIp);
            }
            
            // 构建注册请求
            Map<String, Object> registrationData = buildRegistrationData();
            
            // 发送注册请求
            String registerUrl = gatewayConfig.getUrl() + "/gateway/register";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(registrationData, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                registerUrl,
                HttpMethod.POST,
                request,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("服务注册成功: {}", response.getBody());
            } else {
                logger.error("服务注册失败: {}", response.getStatusCode());
            }

        } catch (Exception e) {
            logger.error("注册服务到网关时发生错误", e);
        }
    }

    /**
     * 构建注册数据
     */
    private Map<String, Object> buildRegistrationData() {
        Map<String, Object> data = new HashMap<>();
        data.put("serviceName", gatewayConfig.getServiceName());
        data.put("ip", gatewayConfig.getIp());
        data.put("port", gatewayConfig.getPort());
        data.put("healthPath", gatewayConfig.getHealthPath());
        data.put("description", gatewayConfig.getDescription());
        data.put("version", gatewayConfig.getVersion());
        data.put("protocol", "http");
        data.put("status", "UP");
        data.put("projectName", gatewayConfig.getServiceName());
        data.put("endpoint", "http://localhost:"+gatewayConfig.getPort());
        return data;
    }
    
    /**
     * 获取本机IP地址
     */
    private String getLocalIpAddress() {
        try {
            // 优先使用localhost，避免网络IP问题
            return "127.0.0.1";
        } catch (Exception e) {
            logger.warn("获取本机IP地址失败，使用默认值", e);
            return "127.0.0.1";
        }
    }
    
    /**
     * 服务下线时从网关注销
     */
    public void unregisterFromGateway() {
        try {
            logger.info("开始从网关注销服务...");
            
            String unregisterUrl = gatewayConfig.getUrl() + "/api/gateway/unregister";
            Map<String, Object> data = new HashMap<>();
            data.put("serviceName", gatewayConfig.getServiceName());
            data.put("ip", gatewayConfig.getIp());
            data.put("port", gatewayConfig.getPort());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                unregisterUrl, 
                HttpMethod.POST, 
                request, 
                String.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("服务注销成功: {}", response.getBody());
            } else {
                logger.error("服务注销失败: {}", response.getStatusCode());
            }
            
        } catch (Exception e) {
            logger.error("从网关注销服务时发生错误", e);
        }
    }
}
