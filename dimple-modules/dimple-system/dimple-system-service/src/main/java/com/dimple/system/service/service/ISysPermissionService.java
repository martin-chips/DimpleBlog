package com.dimple.system.service.service;



import com.dimple.system.api.model.SysUserBO;

import java.util.Set;

/**
 * 权限信息 服务层
 *
 * @author Dimple
 */
public interface ISysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    Set<String> getRolePermission(SysUserBO user);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(SysUserBO user);
}
