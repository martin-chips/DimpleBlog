package com.dimple.modules.endModule.systemManager.repository;

import com.dimple.modules.endModule.systemManager.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : PermissionRepository
 * @description :
 * @date : 12/26/18 19:12
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    Permission findByPermissionId(Integer id);

}
