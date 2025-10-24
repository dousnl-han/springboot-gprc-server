package com.dousnl.listener;

import com.dousnl.service.GatewayRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 应用关闭监听器
 * 在应用关闭时从网关注销服务
 */
@Component
public class ApplicationShutdownListener {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationShutdownListener.class);
    
    @Autowired
    private GatewayRegistrationService gatewayRegistrationService;
    
    /**
     * 监听应用关闭事件
     */
    @EventListener(ContextClosedEvent.class)
    public void onApplicationClosed() {
        logger.info("应用正在关闭，开始从网关注销服务...");
        try {
            gatewayRegistrationService.unregisterFromGateway();
        } catch (Exception e) {
            logger.error("从网关注销服务时发生错误", e);
        }
    }
}
