package com.dimple.modules.BackStageModule.SystemManager.service;

import com.dimple.modules.BackStageModule.SystemManager.bean.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable
     * @return
     */
    Page<Role> getAllRoles(String roleName, String description, Boolean locked, Date startTime, Date endTime, Pageable pageable);

    /***
     * 更新Role的信息
     * @param role
     * @param permissionIds
     * @return
     */
    void updateRole(Role role, Integer[] permissionIds);

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
    Role insertRole(Role role);

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
    Role changeRoleLocked(Integer id, Boolean locked);

    List<Role> getAllRoles();
}
