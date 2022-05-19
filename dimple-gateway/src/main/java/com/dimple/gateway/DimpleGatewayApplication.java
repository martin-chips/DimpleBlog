package com.dimple.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 *
 * @author Dimple
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DimpleGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleGatewayApplication.class, args);
    }
}
