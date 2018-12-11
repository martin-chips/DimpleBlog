package com.dimple.service;

import com.dimple.bean.Permission;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取权限列表树
     *
     * @return
     */
    List<Map<String, Object>> getPermissionTree();

    /**
     * 根据Role的id获取对应的权限树
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getPermissionTreeByRoleId(Integer roleId);

    /**
     * 获取所有的权限数据
     *
     * @param title
     * @param type
     * @return
     */
    List<Permission> getPermissionAll(String title, Integer type);

    /**
     * 删除Permission
     *
     * @param id
     * @return
     */
    int deletePermission(Integer id);

    /**
     * 根据权限的Id获取权限的详细信息
     *
     * @param id
     * @return
     */
    Permission getPermissionById(Integer id);

    /**
     * 获取当前permissionId的上一级的permission的名称
     *
     * @param id
     * @return
     */
    String getPermissionPName(Integer id);

    /**
     * 更新permission
     *
     * @param permission
     * @return
     */
    int updatePermission(Permission permission);

    /**
     * 新增permission
     *
     * @param permission
     * @return
     */
    Integer insertPermission(Permission permission);

    /**
     * 根据permissionId查询与之相关的Role的名称
     *
     * @param permissionId
     * @return
     */
    List<String> getPermissionRelation(Integer permissionId);
}
