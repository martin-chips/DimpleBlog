package com.dimple.modules.BackStageModule.SystemManager.repository;

import com.dimple.modules.BackStageModule.SystemManager.bean.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : UserRoleRepository
 * @description :
 * @date : 12/27/18 14:13
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findAllByUserId(Integer id);

    UserRole findByUserId(Integer id);

    /**
     * 根据用户的Id获取其所有的角色名称
     *
     * @param userId
     * @return
     */
    @Query(value = "select role_name from role r,user_role u where r.role_id=u.role_id and u.user_id=:userId", nativeQuery = true)
    Set<String> getRoleNamesByUserId(@Param("userId") Integer userId);

    @Query(value = "select role_id from user_role where user_id=:userId", nativeQuery = true)
    List<Integer> getRoleIdsByUserId(@Param("userId") Integer userId);
}
