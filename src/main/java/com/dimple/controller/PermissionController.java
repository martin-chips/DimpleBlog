package com.dimple.controller;

import com.dimple.bean.Permission;
import com.dimple.bean.Role;
import com.dimple.service.PermissionService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PermissionController
 * @Description: 权限描述的Controller
 * @Auther: Dimple
 * @Date: 2018/12/8 14:27
 * @Version: 1.0
 */
@Controller
@Api("权限管理")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/page/permission.html")
    public String permissionPage() {
        return "system/permission/permission";
    }

    @GetMapping("/page/permissionAdd/{id}.html")
    public String permissionAddPage(@PathVariable Integer id, Model model) {
        model.addAttribute("pTitle", permissionService.getPermissionPName(id));
        model.addAttribute("pId", id);
        return "system/permission/add";
    }


    @GetMapping("/page/permissionUpdate/{id}.html")
    public String permissionUpdatePage(@PathVariable Integer id, Model model) {
        Permission permission = permissionService.getPermissionById(id);
        String pName = permissionService.getPermissionPName(id);
        model.addAttribute("permission", permission);
        model.addAttribute("pTitle", permissionService.getPermissionPName(id));
        return "system/permission/update";
    }

    @ApiOperation("获取权限树")
    @GetMapping("/api/permissionTree")
    @ResponseBody
    public Result getPermissionTree() {
        List<Map<String, Object>> tree = permissionService.getPermissionTree();
        return ResultUtil.success(tree);
    }

    @ApiOperation("根据RolerId获取权限树")
    @GetMapping("/api/permissionTree/{roleId}")
    @ResponseBody
    public Result getPermissionTreeByRoleId(@PathVariable Integer roleId) {
        List<Map<String, Object>> tree = permissionService.getPermissionTreeByRoleId(roleId);
        return ResultUtil.success(tree);
    }

    @ApiOperation("获取权限的数据")
    @GetMapping("/api/permission")
    @ResponseBody
    public Result getPermissionList(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "status", required = false) Boolean status,
                                    @RequestParam(value = "type", required = false) Integer type) {
        List<Permission> permissions = permissionService.getPermissionAll(title, type, status);
        return ResultUtil.success(permissions);
    }

    @ApiOperation("删除权限前的验证")
    @DeleteMapping("/api/permission/{id}/validate")
    @ResponseBody
    public Result deletePermissionValidate(@PathVariable("id") Integer permissionId) {
        List<String> roleNames = permissionService.getPermissionRelation(permissionId);
        //说明已经没有依赖关系了，可以直接删除
        if (roleNames == null || roleNames.size() == 0) {
            permissionService.deletePermission(permissionId);
            return ResultUtil.success();
        }
        //否则需要返回前端进行用户的再一次确认
        return ResultUtil.success(roleNames.toString());
    }

    @ApiOperation("根据权限ID删除权限信息")
    @DeleteMapping("/api/permission/{id}")
    @ResponseBody
    public Result deletePermission(@PathVariable Integer id) {
        int i = permissionService.deletePermission(id);
        return ResultUtil.success(i);
    }

    @ApiOperation("修改权限信息")
    @PutMapping("/api/permission")
    @ResponseBody
    public Result updatePermission(Permission permission) {
        int i = permissionService.updatePermission(permission);
        return ResultUtil.success();
    }

    @ApiOperation("新增权限")
    @PostMapping("/api/permission")
    @ResponseBody
    public Result insertPermission(Permission permission) {
        Integer integer = permissionService.insertPermission(permission);
        return ResultUtil.success(integer);
    }
}
