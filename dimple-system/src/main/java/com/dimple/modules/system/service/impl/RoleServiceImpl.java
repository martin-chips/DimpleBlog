package com.dimple.modules.system.service.impl;

import com.dimple.exception.BadRequestException;
import com.dimple.exception.EntityExistException;
import com.dimple.modules.system.domain.Menu;
import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.domain.User;
import com.dimple.modules.system.repository.RoleRepository;
import com.dimple.modules.system.repository.UserRepository;
import com.dimple.modules.system.service.RoleService;
import com.dimple.modules.system.service.dto.RoleDTO;
import com.dimple.modules.system.service.dto.RoleQueryCriteria;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.modules.system.service.dto.UserDTO;
import com.dimple.modules.system.service.mapstruct.RoleMapper;
import com.dimple.modules.system.service.mapstruct.RoleSmallMapper;
import com.dimple.utils.FileUtil;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.RedisUtils;
import com.dimple.utils.StringUtils;
import com.dimple.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: RoleServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RoleSmallMapper roleSmallMapper;
    private final RedisUtils redisUtils;
    private final UserRepository userRepository;

    @Override
    public List<RoleDTO> queryAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "level");
        return roleMapper.toDto(roleRepository.findAll(sort));
    }

    @Override
    public List<RoleDTO> queryAll(RoleQueryCriteria criteria) {
        return roleMapper.toDto(roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        Page<Role> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(roleMapper::toDto));
    }

    @Override
    @Cacheable(key = "'id:' + #p0")
    @Transactional(rollbackFor = Exception.class)
    public RoleDTO findById(long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", id);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Role resources) {
        if (roleRepository.findByName(resources.getName()) != null) {
            throw new EntityExistException(Role.class, "username", resources.getName());
        }
        roleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role resources) {
        Role role = roleRepository.findById(resources.getId()).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", resources.getId());

        Role role1 = roleRepository.findByName(resources.getName());

        if (role1 != null && !role1.getId().equals(role.getId())) {
            throw new EntityExistException(Role.class, "username", resources.getName());
        }
        role.setName(resources.getName());
        role.setDescription(resources.getDescription());
        role.setLevel(resources.getLevel());
        roleRepository.save(role);
        // 更新相关缓存
        delCaches(role.getId());
    }

    @Override
    public void updateMenu(Role resources, RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        List<User> users = userRepository.findByRoleId(role.getId());
        Set<Long> userIds = users.stream().map(User::getId).collect(Collectors.toSet());
        // 更新菜单
        role.setMenus(resources.getMenus());
        // 清理缓存
        redisUtils.delByKeys("menu::user:", userIds);
        redisUtils.delByKeys("role::auth:", userIds);
        redisUtils.del("role::id:" + resources.getId());
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void untiedMenu(Long menuId) {
        // 更新菜单
        roleRepository.untiedMenu(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            // 更新相关缓存
            delCaches(id);
        }
        roleRepository.deleteAllByIdIn(ids);
    }

    @Override
    public List<RoleSmallDTO> findByUsersId(Long id) {
        return roleSmallMapper.toDto(new ArrayList<>(roleRepository.findByUserId(id)));
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        Set<RoleDTO> roleDTOS = new HashSet<>();
        for (Role role : roles) {
            roleDTOS.add(findById(role.getId()));
        }
        return Collections.min(roleDTOS.stream().map(RoleDTO::getLevel).collect(Collectors.toList()));
    }

    @Override
    @Cacheable(key = "'auth:' + #p0.id")
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = roleRepository.findByUserId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public void download(List<RoleDTO> roles, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RoleDTO role : roles) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("角色名称", role.getName());
            map.put("角色级别", role.getLevel());
            map.put("描述", role.getDescription());
            map.put("创建日期", role.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 清理缓存
     *
     * @param id /
     */
    public void delCaches(Long id) {
        List<User> users = userRepository.findByRoleId(id);
        Set<Long> userIds = users.stream().map(User::getId).collect(Collectors.toSet());
        redisUtils.delByKeys("data::user:", userIds);
        redisUtils.delByKeys("menu::user:", userIds);
        redisUtils.delByKeys("role::auth:", userIds);
    }

    @Override
    public void verification(Set<Long> ids) {
        if (userRepository.countByRoles(ids) > 0) {
            throw new BadRequestException("所选角色存在用户关联，请解除关联再试！");
        }
    }

    @Override
    public List<Role> findInMenuId(List<Long> menuIds) {
        return roleRepository.findInMenuId(menuIds);
    }
}
