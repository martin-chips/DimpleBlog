package com.dimple.service.impl;

import com.dimple.bean.Permission;
import com.dimple.bean.RolePermission;
import com.dimple.bean.RolePermissionExample;
import com.dimple.dao.PermissionMapper;
import com.dimple.dao.RolePermissionMapper;
import com.dimple.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: PermissionServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 10:05
 * @Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;


    @Override
    public List<Permission> findByRoleId(Integer roleId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        if (rolePermissions == null || rolePermissions.size() == 0) {
            return null;
        }
        List<Permission> permissions = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            Integer id = rolePermission.getPermissionId();
            Permission permission = permissionMapper.selectByPrimaryKey(id);
            permissions.add(permission);
        }
        return permissions;
    }

    @Override
    public List<Map<String, Object>> getPermissionTree() {
        List<Permission> permissions = permissionMapper.selectByExample(null);
        List<Map<String, Object>> permissionTree = handPermissionTree(permissions, null);
        return permissionTree;
    }

    /**
     * 生成权限树
     *
     * @param permissions
     * @return
     */
    private List<Map<String, Object>> handPermissionTree(List<Permission> permissions, List<Integer> checkedPermissionId) {
        List<Map<String, Object>> list = new LinkedList<>();
        for (Permission permission : permissions) {
            Map<String, Object> permissionMap = new HashMap<>();
            permissionMap.put("id", permission.getPermissionId());
            permissionMap.put("pId", permission.getpId());
            permissionMap.put("title", permission.getTitle());
            permissionMap.put("name", permission.getTitle() + "<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + permission.getName() + "</font>");
            if (checkedPermissionId != null && checkedPermissionId.size() != 0) {
                for (Integer id : checkedPermissionId) {
                    if (permission.getPermissionId() == id) {
                        permissionMap.put("checked", true);
                    }
                }
            } else {
                permissionMap.put("checked", false);
            }
            list.add(permissionMap);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getPermissionTreeByRoleId(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        List<Integer> ids = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            Integer permissionId = rolePermission.getPermissionId();
            Permission permission = permissionMapper.selectByPrimaryKey(permissionId);
            ids.add(permission.getPermissionId());
        }
        return handPermissionTree(permissionMapper.selectByExample(null), ids);
    }
}
