package com.dimple.modules.quartz.config;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @className: QuartzConfig
 * @description: 定时任务配置
 * @author: Dimple
 * @date: 06/17/20
 */
@Configuration
public class QuartzConfig {

    /**
     * 注入scheduler到spring
     *
     * @param quartzJobFactory /
     * @return Scheduler
     * @throws Exception /
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

    /**
     * 解决Job中注入Spring Bean为null的问题
     */
    @Component("quartzJobFactory")
    public static class QuartzJobFactory extends AdaptableJobFactory {

        private final AutowireCapableBeanFactory capableBeanFactory;

        public QuartzJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
            this.capableBeanFactory = capableBeanFactory;
        }

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

            //调用父类的方法
            Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }
}
