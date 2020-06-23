package com.dimple.modules.system.repository;

import com.dimple.modules.system.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


/**
 * @className: RoleRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据名称查询
     */
    Role findByName(String name);

    /**
     * 删除多个角色
     */
    void deleteAllByIdIn(Set<Long> ids);

    /**
     * 根据用户ID查询
     */
    @Query(value = "SELECT r.* FROM sys_role r, sys_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = ?1", nativeQuery = true)
    Set<Role> findByUserId(Long id);

    /**
     * 解绑角色菜单
     */
    @Modifying
    @Query(value = "delete from sys_roles_menus where menu_id = ?1", nativeQuery = true)
    void untiedMenu(Long id);

    /**
     * 根据菜单Id查询
     */
    @Query(value = "SELECT r.* FROM sys_role r, sys_roles_menus m WHERE " +
            "r.role_id = m.role_id AND m.menu_id in ?1", nativeQuery = true)
    List<Role> findInMenuId(List<Long> menuIds);
}
