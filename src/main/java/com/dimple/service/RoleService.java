package com.dimple.service;

import com.dimple.bean.Role;

import java.util.List;

/**
 * @ClassName: RoleService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 9:51
 * @Version: 1.0
 */
public interface RoleService {
    /**
     * 根据用户的id找到对应的角色列表
     *
     * @param id 用户的id
     * @return 角色的列表
     */
    List<Role> findByUserId(Integer id);
}
