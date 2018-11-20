package com.dimple.service.impl;

import com.dimple.bean.Role;
import com.dimple.bean.User;
import com.dimple.bean.UserRole;
import com.dimple.bean.UserRoleExample;
import com.dimple.dao.RoleMapper;
import com.dimple.dao.UserRoleMapper;
import com.dimple.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
