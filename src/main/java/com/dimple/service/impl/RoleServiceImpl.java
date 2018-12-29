package com.dimple.service.impl;

import com.dimple.bean.Role;
import com.dimple.bean.RolePermission;
import com.dimple.bean.UserRole;
import com.dimple.repository.RolePermissionRepository;
import com.dimple.repository.RoleRepository;
import com.dimple.repository.UserRepository;
import com.dimple.repository.UserRoleRepository;
import com.dimple.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
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
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public List<Role> findByUserId(Integer id) {

        List<UserRole> userRoles = userRoleRepository.findAllByUserId(id);
        if (userRoles == null || userRoles.size() == 0) {
            return null;
        }
        List<Role> list = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            Integer roleId = userRole.getRoleId();
            Role role = roleRepository.findByRoleId(roleId);
            list.add(role);
        }
        return list;
    }

    @Override
    public Page<Role> getAllRoles(String roleName, String description, Boolean locked, Date startTime,
                                  Date endTime, Pageable pageable) {
        return roleRepository.findAll((Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(roleName)) {
                list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + roleName + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), endTime));
            }
            if (StringUtils.isNotBlank(description)) {
                list.add(criteriaBuilder.like(root.get("description").as(String.class), description));
            }
            if (locked != null) {
                list.add(criteriaBuilder.equal(root.get("locked").as(Boolean.class), locked));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public void updateRole(Role role, Integer[] permissionIds) {
        if (role == null || role.getRoleId() == null) {
            return;
        }
        Role roleDB = roleRepository.findByRoleId(role.getRoleId());
        if (roleDB == null) {
            return;
        }
        if (role.getLocked() == null) {
            role.setLocked(true);
        }
        Role save = roleRepository.save(role);
        //更新RolePermission表
        rolePermissionRepository.deleteAllByRoleId(role.getRoleId());
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getRoleId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionRepository.save(rolePermission);
        }
    }

    @Override
    public int deleteRole(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            roleRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public Role insertRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getRoleName())) {
            return null;
        }
        role.setCreateTime(new Date());
        role.setLocked(false);
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByRoleId(Integer id) {
        return id == null ? null : roleRepository.findByRoleId(id);
    }

    @Override
    public Role changeRoleLocked(Integer id, Boolean locked) {
        Role role = roleRepository.findByRoleId(id);
        if (role == null) {
            return null;
        }
        role.setLocked(!locked);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
