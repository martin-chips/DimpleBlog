package com.dimple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @className: DimpleBlogApplication
 * @description: 启动程序
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.dimple.project.**.mapper")
public class DimpleBlogApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
