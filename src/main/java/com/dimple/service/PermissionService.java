package com.dimple.service;

import com.dimple.bean.Permission;

import java.util.List;

/**
 * @ClassName: PermissionService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 10:05
 * @Version: 1.0
 */
public interface PermissionService {
    /**
     * 根据角色的id返回该角色拥有的权限
     *
     * @return 权限列表
     */
    List<Permission> findByRoleId(Integer roleId);
}
