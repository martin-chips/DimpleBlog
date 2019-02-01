package com.dimple.modules.BackStageModule.SystemMonitor.service;

import com.dimple.framework.constant.ScheduleConstants;
import com.dimple.modules.BackStageModule.SystemMonitor.bean.Job;
import com.dimple.modules.BackStageModule.SystemMonitor.repository.JobRepository;
import com.dimple.modules.BackStageModule.SystemMonitor.util.CronUtil;
import com.dimple.modules.BackStageModule.SystemMonitor.util.ScheduleUtil;
import com.dimple.utils.JpaUpdateUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @className: JobServiceImpl
 * @description:
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobRepository jobRepository;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList) {
            CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduler, job.getJobId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtil.createScheduleJob(scheduler, job);
            } else {
                ScheduleUtil.updateScheduleJob(scheduler, job);
            }
        }
    }


    @Override
    public Boolean checkCronExpression(String expression) {
        return CronUtil.checkCronExpression(expression);
    }


    @Override
    public void deleteJobById(Long[] ids) {
        for (Long id : ids) {
            jobRepository.deleteByJobId(id);
        }
    }

    @Override
    public void pauseJob(Long id) {
        Job byJobId = jobRepository.getByJobId(id);
        if (byJobId == null) {
            return;
        }
        byJobId.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        jobRepository.save(byJobId);
        ScheduleUtil.pauseJob(scheduler, id);
    }

    @Override
    public void resumeJob(Long id) {
        Job byJobId = jobRepository.getByJobId(id);
        if (byJobId == null) {
            return;
        }
        byJobId.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        jobRepository.save(byJobId);
        ScheduleUtil.resumeJob(scheduler, id);
    }

    @Override
    public void run(Long id) {
        ScheduleUtil.run(scheduler, jobRepository.getByJobId(id));
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.getByJobId(id);
    }

    @Override
    public Page<Job> getAllJob(Pageable pageable, String jobName, String methodName, Integer status) {
        return jobRepository.findAll((Specification<Job>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(jobName)) {
                list.add(criteriaBuilder.like(root.get("jobName").as(String.class), "%" + jobName + "%"));
            }
            if (StringUtils.isNotBlank(methodName)) {
                list.add(criteriaBuilder.like(root.get("methodName").as(String.class), "%" + methodName + "%"));
            }
            if (status != null) {
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public void changStatus(Long id, Integer status) {
        Job job = new Job();
        job.setJobId(id);
        job.setStatus(status);
        if (ScheduleConstants.Status.NORMAL.getValue() == status) {
            resumeJob(id);
        } else if (ScheduleConstants.Status.PAUSE.getValue() == status) {
            pauseJob(id);
        }
    }

    @Override
    public void insertJob(Job job) {
        if (job == null || StringUtils.isBlank(job.getJobName()) || StringUtils.isBlank(job.getMethodName()) || StringUtils.isBlank(job.getCronExpression()) || StringUtils.isBlank(job.getJobGroup())) {
            return;
        }
        job.setCreateTime(new Date());
        jobRepository.save(job);
    }

    @Override
    public void updateJob(Job job) {
        if (job == null || job.getJobId() == null || StringUtils.isBlank(job.getJobName()) || StringUtils.isBlank(job.getMethodName()) || StringUtils.isBlank(job.getCronExpression()) || StringUtils.isBlank(job.getJobGroup())) {
            return;
        }
        Job jobDB = jobRepository.getByJobId(job.getJobId());
        JpaUpdateUtil.copyProperties(jobDB, job);
        jobRepository.save(job);
    }
}
