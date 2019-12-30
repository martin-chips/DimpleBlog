package com.dimple.project.system.controller;

import com.dimple.common.constant.Constants;
import com.dimple.common.utils.ServletUtils;
import com.dimple.framework.security.LoginUser;
import com.dimple.framework.security.service.SysLoginService;
import com.dimple.framework.security.service.SysPermissionService;
import com.dimple.framework.security.service.TokenService;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.system.domain.Menu;
import com.dimple.project.system.domain.SysUser;
import com.dimple.project.system.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @className: LoginController
 * @description: 登录验证
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
public class LoginController {
    private final SysLoginService loginService;

    private final MenuService menuService;

    private final SysPermissionService permissionService;

    private final TokenService tokenService;

    public LoginController(SysLoginService loginService, MenuService menuService, SysPermissionService permissionService, TokenService tokenService) {
        this.loginService = loginService;
        this.menuService = menuService;
        this.permissionService = permissionService;
        this.tokenService = tokenService;
    }

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<Menu> menus = menuService.selectMenuTreeByUserId(user.getId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
