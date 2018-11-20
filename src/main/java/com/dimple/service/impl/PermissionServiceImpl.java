package com.dimple.service.impl;

import com.dimple.bean.Permission;
import com.dimple.bean.RolePermission;
import com.dimple.bean.RolePermissionExample;
import com.dimple.dao.PermissionMapper;
import com.dimple.dao.RolePermissionMapper;
import com.dimple.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
}
