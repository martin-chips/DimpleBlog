package com.dimple.modules.endModule.systemManager.service;


import com.dimple.modules.endModule.systemManager.bean.Permission;
import com.dimple.modules.endModule.systemManager.bean.Role;
import com.dimple.modules.endModule.systemManager.bean.RolePermission;
import com.dimple.modules.endModule.systemManager.repository.PermissionRepository;
import com.dimple.modules.endModule.systemManager.repository.RolePermissionRepository;
import com.dimple.modules.endModule.systemManager.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PermissionServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 10:05
 * @Version: 1.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public List<Permission> findByRoleId(Integer roleId) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleId(roleId);
        if (rolePermissions == null || rolePermissions.size() == 0) {
            return null;
        }
        List<Permission> permissions = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            Integer id = rolePermission.getPermissionId();
            Permission permission = permissionRepository.findByPermissionId(id);
            permissions.add(permission);
        }
        return permissions;
    }

    @Override
    public List<Map<String, Object>> getPermissionTree() {
        List<Permission> permissions = permissionRepository.findAll();
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
            permissionMap.put("pId", permission.getPId());
            permissionMap.put("title", permission.getTitle());
            // permissionMap.put("name", permission.getTitle() + "<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + permission.getName() + "</font>");
            permissionMap.put("name", permission.getTitle());
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
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleId(roleId);

        List<Integer> ids = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            //获取permissionId
            Integer permissionId = rolePermission.getPermissionId();
            Permission permission = permissionRepository.findByPermissionId(permissionId);
            ids.add(permission.getPermissionId());
        }
        return handPermissionTree(permissionRepository.findAll(), ids);
    }

    @Override
    public List<Permission> getPermissionAll(String title, Integer type, Boolean status) {
        return permissionRepository.findAll((Specification<Permission>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (type != null) {
                list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), type));
            }
            if (status != null) {
                list.add(criteriaBuilder.equal(root.get("status").as(Boolean.class), status));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        });
    }

    @Override
    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return id == null ? null : permissionRepository.findByPermissionId(id);
    }

    @Override
    public String getPermissionPName(Integer id) {
        if (id == null) {
            return null;
        }
        Permission permission = permissionRepository.findByPermissionId(id);
        if (permission == null) {
            return null;
        }
        Permission permissionP = permissionRepository.findByPermissionId(permission.getPId());
        return permissionP == null ? "主目录" : permissionP.getTitle();
    }

    @Override
    public Permission updatePermission(Permission permission) {
        if (permission.getPermissionId() == null || StringUtils.isBlank(permission.getTitle())) {
            return null;
        }
        return permissionRepository.save(permission);
    }

    @Override
    public Permission insertPermission(Permission permission) {
        if (StringUtils.isBlank(permission.getTitle())) {
            return null;
        }
        return permissionRepository.save(permission);
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
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleId(permissionId);
        //获取到和当前的permission相关的role的id
        if (rolePermissions == null || rolePermissions.size() == 0) {
            return null;
        }
        List<Role> roleName = new LinkedList<>();
        for (RolePermission rolePermission : rolePermissions) {
            Integer roleId = rolePermission.getRoleId();
            Role role = roleRepository.findByRoleId(roleId);
            roleName.add(role);
        }
        return roleName;
    }
}
