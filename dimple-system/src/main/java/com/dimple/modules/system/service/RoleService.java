package com.dimple.modules.system.service;

import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.service.dto.RoleDTO;
import com.dimple.modules.system.service.dto.RoleQueryCriteria;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.modules.system.service.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @className: RoleService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface RoleService {

    /**
     * 查询全部数据
     *
     * @return /
     */
    List<RoleDTO> queryAll();

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    RoleDTO findById(long id);

    /**
     * 创建
     *
     * @param resources /
     */
    void create(Role resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Role resources);

    /**
     * 删除
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDTO> findByUsersId(Long id);

    /**
     * 根据角色查询角色级别
     *
     * @param roles /
     * @return /
     */
    Integer findByRoles(Set<Role> roles);

    /**
     * 修改绑定的菜单
     *
     * @param resources /
     * @param roleDTO   /
     */
    void updateMenu(Role resources, RoleDTO roleDTO);

    /**
     * 解绑菜单
     *
     * @param id /
     */
    void untiedMenu(Long id);

    /**
     * 待条件分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(RoleQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     *
     * @param criteria 条件
     * @return /
     */
    List<RoleDTO> queryAll(RoleQueryCriteria criteria);

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<RoleDTO> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user);

    /**
     * 验证是否被用户关联
     *
     * @param ids /
     */
    void verification(Set<Long> ids);

    /**
     * 根据菜单Id查询
     *
     * @param menuIds /
     * @return /
     */
    List<Role> findInMenuId(List<Long> menuIds);
}
