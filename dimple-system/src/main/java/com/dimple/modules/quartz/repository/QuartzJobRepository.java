package com.dimple.modules.quartz.repository;

import com.dimple.modules.quartz.domain.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * @className: QuartzJobRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Long>, JpaSpecificationExecutor<QuartzJob> {

    /**
     * 查询启用的任务
     *
     * @return List
     */
    List<QuartzJob> findByIsPauseIsFalse();
}
