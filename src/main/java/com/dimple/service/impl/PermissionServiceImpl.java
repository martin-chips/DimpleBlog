package com.dimple.service.impl;

import com.dimple.bean.*;
import com.dimple.dao.PermissionMapper;
import com.dimple.dao.RoleMapper;
import com.dimple.dao.RolePermissionMapper;
import com.dimple.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    RoleMapper roleMapper;

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
        //获取RoleID对应的RolePermission关联表
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        List<Integer> ids = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            //获取permissionId
            Integer permissionId = rolePermission.getPermissionId();
            Permission permission = permissionMapper.selectByPrimaryKey(permissionId);
            ids.add(permission.getPermissionId());
        }
        return handPermissionTree(permissionMapper.selectByExample(null), ids);
    }

    @Override
    public List<Permission> getPermissionAll(String title, Integer type, Boolean status) {
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        return permissions;
    }

    @Override
    public int deletePermission(Integer id) {
        int i = permissionMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Permission getPermissionById(Integer id) {
        if (id == null) {
            return null;
        }
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    @Override
    public String getPermissionPName(Integer id) {
        if (id == null) {
            return null;
        }
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (permission == null) {
            return null;
        }
        Permission permissionP = permissionMapper.selectByPrimaryKey(permission.getpId());
        return permissionP == null ? "主目录" : permissionP.getTitle();
    }

    @Override
    public int updatePermission(Permission permission) {
        if (permission.getPermissionId() == null) {
            return -1;
        }
        int i = permissionMapper.updateByPrimaryKey(permission);
        return i;
    }

    @Override
    public Integer insertPermission(Permission permission) {
        if (StringUtils.isBlank(permission.getTitle())) {
            return -1;
        }
        int i = permissionMapper.insert(permission);
        return i;
    }

    @Override
    public List<String> getPermissionRelation(Integer permissionId) {
        List<Role> roleByPermissionRelation = getRoleByPermissionRelation(permissionId);
        if (roleByPermissionRelation == null || roleByPermissionRelation.size() == 0) {
            return null;
        }
        List<String> roleNames = new LinkedList<>();
        for (Role role : roleByPermissionRelation) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }

    /**
     * 根据Permission的id来获取与之相关的role的信息
     *
     * @param permissionId
     * @return
     */
    private List<Role> getRoleByPermissionRelation(Integer permissionId) {
        if (permissionId == null) {
            return null;
        }
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andPermissionIdEqualTo(permissionId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        //获取到和当前的permission相关的role的id
        if (rolePermissions == null || rolePermissions.size() == 0) {
            return null;
        }
        List<Role> roleName = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            Integer roleId = rolePermission.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roleName.add(role);
        }
        return roleName;
    }
}
