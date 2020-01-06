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
 * @description: Job Runner for load job from database
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
     * get job from database when application run
     */
    @Override
    public void run(ApplicationArguments args) {
        log.info("start inject task");
        List<QuartzJob> quartzJobList = quartzJobService.selectRunningQuartzJobList();
        quartzJobList.forEach(quartzManage::addJob);
        log.info("end inject task,the size of task {}", quartzJobList.size());
    }
}
