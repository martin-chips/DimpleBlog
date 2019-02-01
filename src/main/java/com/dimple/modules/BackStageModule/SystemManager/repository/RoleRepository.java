package com.dimple.modules.BackStageModule.SystemManager.repository;

import com.dimple.modules.BackStageModule.SystemManager.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RoleRepository
 * @description :
 * @date : 12/26/18 19:13
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    Role findByRoleId(Integer id);
}
