package com.dimple.modules.BackStageModule.SystemManager.repository;

import com.dimple.modules.BackStageModule.SystemManager.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

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

    /**
     * 根据role的id联合查询permission的name（权限标识）
     *
     * @param roleId
     * @return
     */
    @Query(value = "select name from permission p,role_permission r where p.permission_id=r.permission_id and r.role_id=:roleId", nativeQuery = true)
    Set<String> getPermissionNamesByRoleId(Integer roleId);
}
