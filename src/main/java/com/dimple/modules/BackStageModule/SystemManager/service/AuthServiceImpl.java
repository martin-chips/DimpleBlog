package com.dimple.modules.BackStageModule.SystemManager.service;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import com.dimple.modules.BackStageModule.SystemManager.repository.PermissionRepository;
import com.dimple.modules.BackStageModule.SystemManager.repository.RolePermissionRepository;
import com.dimple.modules.BackStageModule.SystemManager.repository.UserRepository;
import com.dimple.modules.BackStageModule.SystemManager.repository.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: AuthServiceImpl
 * @description: 授权相关的Service
 * @auther: Owenb
 * @date: 01/31/19
 * @version: 1.0
 */
@Transactional
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public User getUserByLoginId(String loginId) {
        return userRepository.getByUserLoginId(loginId);
    }

    @Override
    public Set<String> getRoleNameByUserId(Integer userId) {
        return userRoleRepository.getRoleNamesByUserId(userId);
    }

    @Override
    public Set<String> getPermissionByUserId(Integer userId) {
        List<Integer> roleIds = userRoleRepository.getRoleIdsByUserId(userId);
        Set<String> permissions = new HashSet<>();
        for (Integer roleId : roleIds) {
            //根据role id 获取permission
            Set<String> permissionNamesByRoleId = permissionRepository.getPermissionNamesByRoleId(roleId);
            if (permissionNamesByRoleId == null || permissionNamesByRoleId.size() == 0 || StringUtils.isBlank(permissionNamesByRoleId.iterator().next())) {
                continue;
            }
            permissions.addAll(permissionNamesByRoleId);
        }
        return permissions;
    }

}
