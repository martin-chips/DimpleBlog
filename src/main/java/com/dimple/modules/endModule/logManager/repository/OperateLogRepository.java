package com.dimple.modules.endModule.logManager.repository;

import com.dimple.modules.endModule.logManager.bean.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: OperateLogRepository
 * @Description:
 * @Auther: Owenb
 * @Date: 12/26/18 19:12
 * @Version: 1.0
 */
@Repository
public interface OperateLogRepository extends JpaRepository<OperateLog, Integer>, JpaSpecificationExecutor<OperateLog> {

    OperateLog findByOperateId(Integer id);

}
