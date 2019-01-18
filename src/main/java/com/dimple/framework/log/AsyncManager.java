package com.dimple.framework.log;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: AsyncManager
 * @description: 异步任务管理器
 * @auther: Dimple
 * @date: 01/14/19 11:34
 * @version: 1.0
 */
@Slf4j
public class AsyncManager {


    //操作延时10ms
    private final int OPERATE_DELAY_TIME = 10;


    private static AsyncManager asyncManager = new AsyncManager();

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    /**
     * 单例模式
     *
     * @return
     */
    public static AsyncManager getAsyncManager() {
        return asyncManager;
    }

    /**
     * 执行任务
     *
     * @param timerTask
     */
    public void execute(TimerTask timerTask) {
        executor.schedule(timerTask, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
