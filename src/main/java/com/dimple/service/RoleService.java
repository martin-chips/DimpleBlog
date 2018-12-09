package com.dimple.service;

import com.dimple.bean.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RoleService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 9:51
 * @Version: 1.0
 */
@CacheConfig(cacheNames = "user")
public interface RoleService {
    /**
     * 根据用户的id找到对应的角色列表
     *
     * @param id 用户的id
     * @return 角色的列表
     */
    @Cacheable(key = "'userRole:'+#id")
    List<Role> findByUserId(Integer id);

    /**
     * 获取所有的角色列表
     *
     * @param roleName
     * @param description
     * @param locked
     * @param startTime
     * @param endTime
     * @return
     */
    List<Role> getAllRoles(String roleName, String description, Boolean locked, Date startTime, Date endTime);

    /***
     * 更新Role的信息
     * @param role
     * @param permissionIds
     * @return
     */
    int updateRole(Role role, Integer[] permissionIds);

    /**
     * 删除Role
     *
     * @param ids
     * @return
     */
    int deleteRole(Integer[] ids);

    /**
     * 新增Role
     *
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 根据id获取Role
     *
     * @param id
     * @return
     */
    Role getRoleByRoleId(Integer id);

    /**
     * 根据Role的id切换锁定状态
     *
     * @param id
     * @param locked
     * @return
     */
    Integer changeRoleLocked(Integer id, Boolean locked);
}
