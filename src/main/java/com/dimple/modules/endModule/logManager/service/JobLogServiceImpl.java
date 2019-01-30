package com.dimple.modules.endModule.logManager.service;

import com.dimple.modules.endModule.logManager.bean.JobLog;
import com.dimple.modules.endModule.logManager.repository.JobLogRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @className: JobLogServiceImpl
 * @description:
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Transactional
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    JobLogRepository jobLogRepository;

    @Override
    public void insertJobLog(JobLog jobLog) {
        jobLogRepository.save(jobLog);
    }

    @Override
    public void deleteJobLogById(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        for (Long id : ids) {
            jobLogRepository.deleteById(id);
        }
    }

    @Override
    public void cleanJobLog() {
        jobLogRepository.deleteAll();
    }

    @Override
    public Page<JobLog> getAllJobLog(String methodName, String jobName, Boolean status, Date startTime, Date endTime, Pageable pageable) {
        return jobLogRepository.findAll((Specification<JobLog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(methodName)) {
                list.add(criteriaBuilder.like(root.get("methodName").as(String.class), "%" + methodName + "%"));
            }
            if (StringUtils.isNotBlank(jobName)) {
                list.add(criteriaBuilder.like(root.get("jobName").as(String.class), "%" + jobName + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
            }
            if (status != null) {
                list.add(criteriaBuilder.equal(root.get("status").as(Boolean.class), status));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public JobLog getJobLogById(Long id) {
        return jobLogRepository.getByJobLogId(id);
    }
}
