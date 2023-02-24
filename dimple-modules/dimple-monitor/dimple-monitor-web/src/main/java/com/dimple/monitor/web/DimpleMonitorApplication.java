package com.dimple.monitor.web;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableDimpleFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 *
 * @author Dimple
 */
@EnableAdminServer
@EnableCustomConfig
@EnableCustomSwagger2
@EnableDimpleFeignClients
@SpringBootApplication(scanBasePackages = {"com.dimple.monitor"})
public class DimpleMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleMonitorApplication.class, args);
    }
}
