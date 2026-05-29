package com.dousnl.test;


import com.dousnl.config.GatewayConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GatewayTest {

    @Autowired
    private GatewayConfig gatewayConfig;

    @Test
    public void test() {
        System.out.println(gatewayConfig.getDescription());
    }
}
