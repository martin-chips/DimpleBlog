package com.dimple.modules.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 *
 * @author Dimple
 */
@EnableAdminServer
@SpringBootApplication
public class DimpleMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleMonitorApplication.class, args);
    }
}
