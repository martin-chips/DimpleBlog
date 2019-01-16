package com.dimple.framework.log;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @className: ShutdownManager
 * @description:
 * @auther: Dimple
 * @date: 01/16/19 10:20
 * @version: 1.0
 */
@Component
public class ShutdownManager {

    /**
     * 确保应用退出的时候能关闭后台线程
     */
    @PreDestroy
    public void destory() {
        AsyncManager.getAsyncManager().shutdownAndAwaitTermination();
    }

}
