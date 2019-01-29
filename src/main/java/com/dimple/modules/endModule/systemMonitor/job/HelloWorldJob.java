package com.dimple.modules.endModule.systemMonitor.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @className: HelloWorldJob
 * @description: 业务Job
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Component("job")
@Slf4j
public class HelloWorldJob {

    public void task() {
        log.info("Hello World!");
    }

    /**
     * 检查友链
     */
    public void checkLinks() {
        log.info("check links");
    }

    /**
     * 同步prod数据库到test数据库
     */
    public void syncDB() {
        log.info("sync prod data to test data");
    }



}
