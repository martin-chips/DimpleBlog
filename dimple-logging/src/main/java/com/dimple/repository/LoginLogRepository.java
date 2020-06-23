package com.dimple.repository;

import com.dimple.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @className: LoginLogRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long>, JpaSpecificationExecutor<LoginLog> {

}
