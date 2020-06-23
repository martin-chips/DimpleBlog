package com.dimple.repository;

import com.dimple.domain.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @className: VisitLogRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Repository
public interface VisitLogRepository extends JpaRepository<VisitLog, Long>, JpaSpecificationExecutor<VisitLog> {

}
