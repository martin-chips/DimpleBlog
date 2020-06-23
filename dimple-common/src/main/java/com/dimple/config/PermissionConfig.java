package com.dimple.config;

import com.dimple.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @className: PermissionConfig
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service(value = "ps")
public class PermissionConfig {

    public Boolean check(String... permissions) {
        // 获取当前用户的所有权限
        List<String> permissionList = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return permissionList.contains("admin") || Arrays.stream(permissions).anyMatch(permissionList::contains);
    }
}
