package com.dimple.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dimple.exception.BadRequestException;
import com.dimple.exception.EntityExistException;
import com.dimple.modules.system.domain.Menu;
import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.domain.User;
import com.dimple.modules.system.domain.vo.MenuMetaVO;
import com.dimple.modules.system.domain.vo.MenuVO;
import com.dimple.modules.system.repository.MenuRepository;
import com.dimple.modules.system.repository.UserRepository;
import com.dimple.modules.system.service.MenuService;
import com.dimple.modules.system.service.RoleService;
import com.dimple.modules.system.service.dto.MenuDTO;
import com.dimple.modules.system.service.dto.MenuQueryCriteria;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.modules.system.service.mapstruct.MenuMapper;
import com.dimple.utils.FileUtil;
import com.dimple.utils.QueryHelp;
import com.dimple.utils.RedisUtils;
import com.dimple.utils.StringUtils;
import com.dimple.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: MenuServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final MenuMapper menuMapper;
    private final RoleService roleService;
    private final RedisUtils redisUtils;

    @Override
    public List<MenuDTO> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception {
        Sort sort = new Sort(Sort.Direction.ASC, "menuSort");
        if (isQuery) {
            criteria.setPidIsNull(true);
            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
            for (Field field : fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object val = field.get(criteria);
                if ("pidIsNull".equals(field.getName())) {
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        return menuMapper.toDto(menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), sort));
    }

    @Override
//    @Cacheable(key = "'id:' + #p0")
    public MenuDTO findById(long id) {
        Menu menu = menuRepository.findById(id).orElseGet(Menu::new);
        ValidationUtil.isNull(menu.getId(), "Menu", "id", id);
        return menuMapper.toDto(menu);
    }

    /**
     * 用户角色改变时需清理缓存
     *
     * @param currentUserId /
     * @return /
     */
    @Override
//    @Cacheable(key = "'user:' + #p0")
    public List<MenuDTO> findByUser(Long currentUserId) {
        List<RoleSmallDTO> roles = roleService.findByUsersId(currentUserId);
        Set<Long> roleIds = roles.stream().map(RoleSmallDTO::getId).collect(Collectors.toSet());
        LinkedHashSet<Menu> menus = menuRepository.findByRoleIdsAndTypeNot(roleIds, 2);
        return menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Menu resources) {
        if (menuRepository.findByTitle(resources.getTitle()) != null) {
            throw new EntityExistException(Menu.class, "title", resources.getTitle());
        }
        if (StringUtils.isNotBlank(resources.getComponentName())) {
            if (menuRepository.findByComponentName(resources.getComponentName()) != null) {
                throw new EntityExistException(Menu.class, "componentName", resources.getComponentName());
            }
        }
        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }
        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http) || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        menuRepository.save(resources);
        // 计算子节点数目
        resources.setSubCount(0);
        // 更新父节点菜单数目
        updateSubCnt(resources.getPid());
        redisUtils.del("menu::pid:" + (resources.getPid() == null ? 0 : resources.getPid()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Menu resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Menu menu = menuRepository.findById(resources.getId()).orElseGet(Menu::new);
        ValidationUtil.isNull(menu.getId(), "Permission", "id", resources.getId());

        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http) || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        Menu menu1 = menuRepository.findByTitle(resources.getTitle());

        if (menu1 != null && !menu1.getId().equals(menu.getId())) {
            throw new EntityExistException(Menu.class, "title", resources.getTitle());
        }

        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }

        // 记录的父节点ID
        Long oldPid = menu.getPid();
        Long newPid = resources.getPid();

        if (StringUtils.isNotBlank(resources.getComponentName())) {
            menu1 = menuRepository.findByComponentName(resources.getComponentName());
            if (menu1 != null && !menu1.getId().equals(menu.getId())) {
                throw new EntityExistException(Menu.class, "componentName", resources.getComponentName());
            }
        }
        menu.setTitle(resources.getTitle());
        menu.setComponent(resources.getComponent());
        menu.setPath(resources.getPath());
        menu.setIcon(resources.getIcon());
        menu.setIFrame(resources.getIFrame());
        menu.setPid(resources.getPid());
        menu.setMenuSort(resources.getMenuSort());
        menu.setCache(resources.getCache());
        menu.setHidden(resources.getHidden());
        menu.setComponentName(resources.getComponentName());
        menu.setPermission(resources.getPermission());
        menu.setType(resources.getType());
        menuRepository.save(menu);
        // 计算父级菜单节点数目
        updateSubCnt(oldPid);
        updateSubCnt(newPid);
        // 清理缓存
        delCaches(resources.getId(), oldPid, newPid);
    }

    @Override
    public Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet) {
        // 递归找出待删除的菜单
        for (Menu menu1 : menuList) {
            menuSet.add(menu1);
            List<Menu> menus = menuRepository.findByPid(menu1.getId());
            if (menus != null && menus.size() != 0) {
                getDeleteMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Menu> menuSet) {
        for (Menu menu : menuSet) {
            // 清理缓存
            delCaches(menu.getId(), menu.getPid(), null);
            roleService.untiedMenu(menu.getId());
            menuRepository.deleteById(menu.getId());
            updateSubCnt(menu.getPid());
        }
    }

    @Override
    @Cacheable(key = "'pid:' + #p0")
    public List<MenuDTO> getMenus(Long pid) {
        List<Menu> menus;
        if (pid != null && !pid.equals(0L)) {
            menus = menuRepository.findByPid(pid);
        } else {
            menus = menuRepository.findByPidIsNull();
        }
        return menuMapper.toDto(menus);
    }

    @Override
    public List<MenuDTO> getSuperior(MenuDTO menuDto, List<Menu> menus) {
        if (menuDto.getPid() == null) {
            menus.addAll(menuRepository.findByPidIsNull());
            return menuMapper.toDto(menus);
        }
        menus.addAll(menuRepository.findByPid(menuDto.getPid()));
        return getSuperior(findById(menuDto.getPid()), menus);
    }

    @Override
    public List<MenuDTO> buildTree(List<MenuDTO> menuDTOS) {
        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO menuDTO : menuDTOS) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (MenuDTO it : menuDTOS) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menuDTOS.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<MenuVO> buildMenus(List<MenuDTO> menuDTOS) {
        List<MenuVO> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDTOList = menuDTO.getChildren();
                        MenuVO menuVo = new MenuVO();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getTitle());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == null) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVO(menuDTO.getTitle(), menuDTO.getIcon(), !menuDTO.getCache()));
                        if (menuDTOList != null && menuDTOList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == null) {
                            MenuVO menuVO1 = new MenuVO();
                            menuVO1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVO1.setPath("index");
                                menuVO1.setName(menuVo.getName());
                                menuVO1.setComponent(menuVo.getComponent());
                            } else {
                                menuVO1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVO> list1 = new ArrayList<>();
                            list1.add(menuVO1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    @Override
    public Menu findOne(Long id) {
        Menu menu = menuRepository.findById(id).orElseGet(Menu::new);
        ValidationUtil.isNull(menu.getId(), "Menu", "id", id);
        return menu;
    }

    @Override
    public void download(List<MenuDTO> menuDTOS, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOS) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("菜单标题", menuDTO.getTitle());
            map.put("菜单类型", menuDTO.getType() == null ? "目录" : menuDTO.getType() == 1 ? "菜单" : "按钮");
            map.put("权限标识", menuDTO.getPermission());
            map.put("外链菜单", menuDTO.getIFrame() ? "是" : "否");
            map.put("菜单可见", menuDTO.getHidden() ? "否" : "是");
            map.put("是否缓存", menuDTO.getCache() ? "是" : "否");
            map.put("创建日期", menuDTO.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    private void updateSubCnt(Long menuId) {
        if (menuId != null) {
            int count = menuRepository.countByPid(menuId);
            menuRepository.updateSubCntById(count, menuId);
        }
    }

    /**
     * 清理缓存
     *
     * @param id     菜单ID
     * @param oldPid 旧的菜单父级ID
     * @param newPid 新的菜单父级ID
     */
    public void delCaches(Long id, Long oldPid, Long newPid) {
        List<User> users = userRepository.findByMenuId(id);
        redisUtils.del("menu::id:" + id);
        redisUtils.delByKeys("menu::user:", users.stream().map(User::getId).collect(Collectors.toSet()));
        redisUtils.del("menu::pid:" + (oldPid == null ? 0 : oldPid));
        redisUtils.del("menu::pid:" + (newPid == null ? 0 : newPid));
        // 清除 Role 缓存
        List<Role> roles = roleService.findInMenuId(new ArrayList<Long>() {{
            add(id);
            add(newPid == null ? 0 : newPid);
        }});
        redisUtils.delByKeys("role::id:", roles.stream().map(Role::getId).collect(Collectors.toSet()));
    }
}
