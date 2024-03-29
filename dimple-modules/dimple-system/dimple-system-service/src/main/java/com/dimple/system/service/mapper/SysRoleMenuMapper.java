package com.dimple.system.service.mapper;


import com.dimple.system.service.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 *
 * @author Dimple
 */
 public interface SysRoleMenuMapper {
    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return affected lines
     */
     int checkMenuExistRole(Long menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param roleId 角色ID
     * @return affected lines
     */
     int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 批量删除角色菜单关联信息
     *
     * @param ids 需要删除的数据ID
     * @return affected lines
     */
     int deleteRoleMenu(Long[] ids);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单列表
     * @return affected lines
     */
     int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
