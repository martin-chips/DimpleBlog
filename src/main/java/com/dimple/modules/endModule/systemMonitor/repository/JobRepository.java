package com.dimple.modules.endModule.systemMonitor.repository;

import com.dimple.modules.endModule.systemMonitor.bean.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @className: JobRepository
 * @description:
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    Job getByJobId(Long id);

    int deleteByJobId(Long id);

}
