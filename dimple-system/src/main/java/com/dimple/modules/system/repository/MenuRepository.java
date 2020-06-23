package com.dimple.modules.system.repository;

import com.dimple.modules.system.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @className: MenuRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    /**
     * 根据菜单标题查询
     */
    Menu findByTitle(String title);

    /**
     * 根据组件名称查询
     */
    Menu findByComponentName(String name);

    /**
     * 根据菜单的 PID 查询
     */
    List<Menu> findByPid(long pid);

    /**
     * 查询顶级菜单
     */
    List<Menu> findByPidIsNull();

    /**
     * 根据角色ID与菜单类型查询菜单
     */
    @Query(value = "SELECT m.* FROM sys_menu m, sys_roles_menus r WHERE " +
            "m.menu_id = r.menu_id AND r.role_id IN ?1 AND type != ?2 order by m.menu_sort asc", nativeQuery = true)
    LinkedHashSet<Menu> findByRoleIdsAndTypeNot(Set<Long> roleIds, int type);

    /**
     * 获取节点数量
     */
    int countByPid(Long id);

    /**
     * 更新节点数目
     */
    @Modifying
    @Query(value = " update sys_menu set sub_count = ?1 where menu_id = ?2 ", nativeQuery = true)
    void updateSubCntById(int count, Long menuId);
}
