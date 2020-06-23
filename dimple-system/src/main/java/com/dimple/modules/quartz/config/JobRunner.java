package com.dimple.modules.quartz.config;

import com.dimple.modules.quartz.domain.QuartzJob;
import com.dimple.modules.quartz.repository.QuartzJobRepository;
import com.dimple.modules.quartz.utils.QuartzManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @className: JobRunner
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JobRunner implements ApplicationRunner {

    private final QuartzJobRepository quartzJobRepository;
    private final QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("start inject job.");
        List<QuartzJob> quartzJobs = quartzJobRepository.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzManage::addJob);
        log.info("finished inject job ,job size is [{}].", quartzJobs.size());
    }
}
