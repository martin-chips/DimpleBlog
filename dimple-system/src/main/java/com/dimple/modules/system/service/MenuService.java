package com.dimple.modules.system.service;

import com.dimple.modules.system.domain.Menu;
import com.dimple.modules.system.service.dto.MenuDTO;
import com.dimple.modules.system.service.dto.MenuQueryCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @className: MenuService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface MenuService {

    /**
     * 查询全部数据
     *
     * @param criteria 条件
     * @param isQuery  /
     * @return /
     * @throws Exception /
     */
    List<MenuDTO> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    MenuDTO findById(long id);

    /**
     * 创建
     *
     * @param resources /
     */
    void create(Menu resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Menu resources);

    /**
     * 获取待删除的菜单
     *
     * @param menuList /
     * @param menuSet  /
     * @return /
     */
    Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet);

    /**
     * 构建菜单树
     *
     * @param menuDTOS 原始数据
     * @return /
     */
    List<MenuDTO> buildTree(List<MenuDTO> menuDTOS);

    /**
     * 构建菜单树
     *
     * @param menuDTOS /
     * @return /
     */
    Object buildMenus(List<MenuDTO> menuDTOS);

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    Menu findOne(Long id);

    /**
     * 删除
     *
     * @param menuSet /
     */
    void delete(Set<Menu> menuSet);

    /**
     * 导出
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<MenuDTO> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 懒加载菜单数据
     *
     * @param pid /
     * @return /
     */
    List<MenuDTO> getMenus(Long pid);

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menuDto /
     * @param objects /
     * @return /
     */
    List<MenuDTO> getSuperior(MenuDTO menuDto, List<Menu> objects);

    /**
     * 根据当前用户获取菜单
     *
     * @param currentUserId /
     * @return /
     */
    List<MenuDTO> findByUser(Long currentUserId);
}
