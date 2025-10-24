package com.dousnl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 网关配置类
 */
@Component
@ConfigurationProperties(prefix = "gateway")
public class GatewayConfig {
    
    /**
     * 网关地址
     */
    private String url = "http://localhost:8080";
    
    /**
     * 服务名称
     */
    private String serviceName;
    
    /**
     * 服务端口
     */
    private Integer port;
    
    /**
     * 服务IP地址
     */
    private String ip;
    
    /**
     * 健康检查路径
     */
    private String healthPath = "/actuator/health";
    
    /**
     * 服务描述
     */
    private String description = "gRPC服务";
    
    /**
     * 服务版本
     */
    private String version = "1.0.0";
    
    // Getters and Setters
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public Integer getPort() {
        return port;
    }
    
    public void setPort(Integer port) {
        this.port = port;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getHealthPath() {
        return healthPath;
    }
    
    public void setHealthPath(String healthPath) {
        this.healthPath = healthPath;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
}

