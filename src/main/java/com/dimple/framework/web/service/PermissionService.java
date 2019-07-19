package com.dimple.framework.web.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @className: PermissionService
 * @description:  js调用 thymeleaf 实现按钮权限可见性
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Service("permission")
public class PermissionService {
    public String hasPermi(String permission) {
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

}
