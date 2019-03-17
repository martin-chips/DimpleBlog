package com.dimple.project.monitor.job.task;

import org.springframework.stereotype.Component;

/**
 * @className: RyTask
 * @description: 定时任务调度测试
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Component("ryTask")
public class RyTask {
    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}
