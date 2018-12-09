package com.dimple.service.impl;

import com.dimple.bean.*;
import com.dimple.dao.RoleMapper;
import com.dimple.dao.RolePermissionMapper;
import com.dimple.dao.UserRoleMapper;
import com.dimple.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: RoleService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 9:51
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> findByUserId(Integer id) {
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles == null || userRoles.size() == 0) {
            return null;
        }
        List<Role> list = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            Integer roleId = userRole.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            list.add(role);
        }
        return list;
    }

    @Override
    public List<Role> getAllRoles(String roleName, String description, Boolean locked, Date startTime, Date endTime) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if (startTime != null && endTime != null) {
            criteria.andCreateTimeBetween(startTime, endTime);
        } else if (startTime == null && endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(endTime);
        } else if (startTime != null && endTime == null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        if (StringUtils.isNotBlank(roleName)) {
            criteria.andRoleNameLike(roleName);
        }
        if (StringUtils.isNotBlank(description)) {
            criteria.andDescriptionLike(description);
        }
        if (locked != null) {
            criteria.andLockedEqualTo(locked);
        }
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    @Override
    public int updateRole(Role role, Integer[] permissionIds) {
        if (role == null || role.getRoleId() == null) {
            return -1;
        }
        Role roleDB = roleMapper.selectByPrimaryKey(role.getRoleId());
        if (roleDB == null) {
            return -1;
        }
        if (roleDB.getLocked() == null) {
            roleDB.setLocked(true);
        }
        int i = roleMapper.updateByPrimaryKeySelective(role);
        //更新RolePermission表
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(role.getRoleId());
        rolePermissionMapper.deleteByExample(rolePermissionExample);
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getRoleId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
        return i;
    }

    @Override
    public int deleteRole(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            count += roleMapper.deleteByPrimaryKey(id);
        }
        return count;
    }

    @Override
    public int insertRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getRoleName())) {
            return -1;
        }
        role.setCreateTime(new Date());
        role.setLocked(false);
        int i = roleMapper.insert(role);
        return i;
    }

    @Override
    public Role getRoleByRoleId(Integer id) {
        if (id == null) {
            return null;
        }
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public Integer changeRoleLocked(Integer id, Boolean locked) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return -1;
        }
        role.setLocked(!locked);
        int i = roleMapper.updateByPrimaryKeySelective(role);
        return i;
    }
}
