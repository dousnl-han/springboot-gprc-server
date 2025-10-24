# 网关注册功能说明

## 功能概述

本项目已集成自动网关注册功能，服务启动时会自动注册到网关，服务关闭时会自动从网关注销。

## 配置说明

### 1. 网关配置 (application.yaml)

```yaml
# 网关配置
gateway:
  url: http://localhost:8080  # 网关地址
  service-name: ${spring.application.name}
  port: ${server.port}
  health-path: /actuator/health
  description: "gRPC服务"
  version: "1.0.0"
```

### 2. 健康检查配置

```yaml
# 健康检查配置
management:
  endpoints:
    web:
      exposure:
        include: health,info
  endpoint:
    health:
      show-details: always
```

## 核心组件

### 1. GatewayConfig
- 网关配置类，用于配置网关地址、服务信息等

### 2. GatewayRegistrationService
- 网关注册服务，负责注册和注销服务
- 在应用启动完成后自动注册到网关
- 在应用关闭时自动从网关注销

### 3. ApplicationShutdownListener
- 应用关闭监听器，确保服务关闭时能从网关注销

## 注册流程

1. **服务启动时**：
   - 应用启动完成后触发 `ApplicationReadyEvent` 事件
   - `GatewayRegistrationService.registerToGateway()` 方法被调用
   - 自动获取本机IP地址
   - 向网关发送注册请求

2. **服务关闭时**：
   - 应用关闭时触发 `ContextClosedEvent` 事件
   - `ApplicationShutdownListener` 监听器被触发
   - 调用 `GatewayRegistrationService.unregisterFromGateway()` 方法
   - 向网关发送注销请求

## 注册数据格式

发送到网关的注册数据格式：

```json
{
  "serviceName": "dousnl-grpc-server",
  "ip": "192.168.1.100",
  "port": 8081,
  "healthPath": "/actuator/health",
  "description": "gRPC服务",
  "version": "1.0.0",
  "protocol": "grpc",
  "status": "UP"
}
```

## 网关接口要求

网关需要提供以下接口：

### 注册接口
- **URL**: `POST {gateway.url}/api/gateway/register`
- **Content-Type**: `application/json`
- **请求体**: 上述注册数据格式

### 注销接口
- **URL**: `POST {gateway.url}/api/gateway/unregister`
- **Content-Type**: `application/json`
- **请求体**: 
```json
{
  "serviceName": "dousnl-grpc-server",
  "ip": "192.168.1.100",
  "port": 8081
}
```

## 使用说明

1. **启动服务**：
   ```bash
   mvn spring-boot:run
   ```

2. **查看日志**：
   - 服务启动时会看到 "开始注册服务到网关..." 的日志
   - 注册成功会看到 "服务注册成功" 的日志
   - 服务关闭时会看到 "开始从网关注销服务..." 的日志

3. **健康检查**：
   - 访问 `http://localhost:8081/actuator/health` 查看服务健康状态

## 注意事项

1. 确保网关服务正在运行且可访问
2. 确保网关提供了正确的注册和注销接口
3. 如果网关不可用，服务仍会正常启动，但会在日志中记录错误
4. 可以通过修改 `application.yaml` 中的 `gateway.url` 来配置网关地址
