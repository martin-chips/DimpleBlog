package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.Role;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @className: RoleMapper
 * @description: 角色表 数据层
 * @author: Dimple
 * @date: 10/22/19
 */
public interface RoleMapper {
    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> selectRolePermissionByUserId(Long userId);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Integer> selectRoleListByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    Role selectRoleById(Long roleId);

    /**
     * 根据用户ID查询角色
     *
     * @param userName 用户名
     * @return 角色列表
     */
    List<Role> selectRolesByUserName(String userName);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    Role checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    Role checkRoleKeyUnique(String roleKey);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(Role role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(Role role);

    /**
     * 通过角色ID删除角色
     *
     * @param ids 角色ID
     * @return 结果
     */
    int deleteRoleByIds(@Param("ids") Long[] ids, @Param("username") String username);

}
