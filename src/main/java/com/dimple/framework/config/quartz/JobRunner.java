package com.dimple.framework.config.quartz;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @className: JobRunner
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Component
public class JobRunner implements ApplicationRunner {

    /**
     * 方便项目重启的时候重新加载任务
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
