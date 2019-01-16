package com.dimple.framework.log;

import com.dimple.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
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


    private static ScheduledExecutorService executorService;

    private static AsyncManager asyncManager = new AsyncManager();

    /**
     * 单例模式
     *
     * @return
     */
    public static AsyncManager getAsyncManager() {
        executorService = SpringUtil.getBean("scheduledExecutorService");
        return asyncManager;
    }

    /**
     * 执行任务
     *
     * @param timerTask
     */
    public void execute(TimerTask timerTask) {
        executorService.schedule(timerTask, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }


    public void shutdownAndAwaitTermination() {
        log.info("======关闭后台任务线程池======");
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
                    executorService.shutdown();
                    if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                executorService.shutdown();
                Thread.currentThread().interrupt();
            }
        }
    }
}
