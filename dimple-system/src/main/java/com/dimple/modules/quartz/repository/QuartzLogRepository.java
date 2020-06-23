package com.dimple.modules.quartz.repository;

import com.dimple.modules.quartz.domain.QuartzLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @className: QuartzLogRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface QuartzLogRepository extends JpaRepository<QuartzLog, Long>, JpaSpecificationExecutor<QuartzLog> {

}
