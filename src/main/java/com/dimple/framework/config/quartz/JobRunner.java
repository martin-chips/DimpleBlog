package com.dimple.framework.config.quartz;

import com.dimple.project.tool.domain.QuartzJob;
import com.dimple.project.tool.service.QuartzJobService;
import com.dimple.project.tool.utils.QuartzManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: JobRunner
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Component
@Slf4j
public class JobRunner implements ApplicationRunner {
    @Autowired
    QuartzManage quartzManage;
    @Autowired
    QuartzJobService quartzJobService;

    /**
     * 方便项目重启的时候重新加载任务
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始注入定时任务");
        List<QuartzJob> quartzJobList = quartzJobService.selectRunningQuartzJobList();
        quartzJobList.forEach(quartzManage::addJob);
        log.info("任务注入完成");
    }
}
