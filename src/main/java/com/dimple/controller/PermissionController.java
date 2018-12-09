package com.dimple.controller;

import com.dimple.service.PermissionService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PermissionController
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/8 14:27
 * @Version: 1.0
 */
@Controller
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/sysyem/permission/permission.html")
    public String permissionPage() {
        return "system/permission/permission";
    }

    @GetMapping("/system/permission/permissionTree")
    @ResponseBody
    public Result getPermissionTree() {
        List<Map<String, Object>> tree = permissionService.getPermissionTree();
        return ResultUtil.success(tree);
    }

    /**
     * 根据Role的id获取其对应的权限树
     *
     * @param roleId
     * @return
     */
    @GetMapping("/system/permission/permissionTree/{roleId}")
    @ResponseBody
    public Result getPermissionTreeByRoleId(@PathVariable Integer roleId) {
        List<Map<String, Object>> tree = permissionService.getPermissionTreeByRoleId(roleId);
        return ResultUtil.success(tree);
    }


}
