package com.dimple.config.thread;

import com.dimple.utils.SpringContextHolder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: ThreadPoolExecutorUtil
 * @description: 用于获取自定义线程池
 * @author: Dimple
 * @date: 06/17/20
 */
public class ThreadPoolExecutorUtil {

    public static ThreadPoolExecutor getPoll() {
        AsyncTaskProperties properties = SpringContextHolder.getBean(AsyncTaskProperties.class);
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaxPoolSize(),
                properties.getKeepAliveSeconds(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(properties.getQueueCapacity()),
                new TheadFactoryName()
        );
    }
}
