package com.dimple.modules.BackStageModule.SystemManager.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.SystemManager.bean.Permission;
import com.dimple.modules.BackStageModule.SystemManager.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

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
    @RequiresPermissions("page:permission:view")
    @ApiIgnore
    public String permissionPage() {
        return "system/permission/permission";
    }

    @GetMapping("/page/permissionAdd/{id}.html")
    @RequiresPermissions("page:permissionInsert:view")
    @ApiIgnore
    public String permissionAddPage(@PathVariable(value = "id") Integer parentId, Model model) {
        Permission permission = null;
        if (parentId == 0) {
            permission = new Permission();
            permission.setName("主目录");
            permission.setPermissionId(0);
        } else {
            permission = permissionService.getPermissionById(parentId);
        }
        model.addAttribute("permission", permission);
        return "system/permission/add";
    }

    @ApiIgnore
    @GetMapping("/page/permissionUpdate/{id}.html")
    @RequiresPermissions("page:permissionUpdate:view")
    public String permissionUpdatePage(@PathVariable Integer id, Model model) {
        Permission permission = permissionService.getPermissionById(id);
        model.addAttribute("permission", permission);
        model.addAttribute("pTitle", permissionService.getPermissionPName(id));
        return "system/permission/update";
    }

    @ApiIgnore
    @GetMapping("/page/permissionTree/{id}")
    @RequiresPermissions("page:permissionTree:view")
    @Log(title = "权限管理", operateType = OperateType.SELECT)
    public String getPermissionInfo(@PathVariable Integer id, Model model) {
        if (id == 0) {
            Permission permission = new Permission();
            permission.setPermissionId(0);
            permission.setTitle("");
            model.addAttribute("permission", permission);
        }
        model.addAttribute("permission", permissionService.getPermissionById(id));
        return "system/permission/tree";
    }

    @ApiOperation("获取权限树")
    @GetMapping("/api/permissionTree")
    @Log(title = "权限管理", operateType = OperateType.SELECT)
    @RequiresPermissions("SystemManager:permission:view")
    @ResponseBody
    public Result getPermissionTree() {
        List<Map<String, Object>> tree = permissionService.getPermissionTree();
        return ResultUtil.success(tree);
    }

    @ApiOperation("根据RolerId获取权限树")
    @GetMapping("/api/permissionTree/{roleId}")
    @RequiresPermissions("SystemManager:permissionTree:view")
    @Log(title = "权限管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result getPermissionTreeByRoleId(@PathVariable Integer roleId) {
        List<Map<String, Object>> tree = permissionService.getPermissionTreeByRoleId(roleId);
        return ResultUtil.success(tree);
    }

    @ApiOperation("获取权限的数据")
    @GetMapping("/api/permission")
    @RequiresPermissions("SystemManager:permissionTree:query")
    @Log(title = "权限管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result getPermissionList(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "status", required = false) Boolean status,
                                    @RequestParam(value = "type", required = false) Integer type) {
        List<Permission> permissions = permissionService.getPermissionAll(title, type, status);
        return ResultUtil.success(permissions);
    }

    @ApiOperation("删除权限前的验证")
    @DeleteMapping("/api/permission/{id}/validate")
    @Log(title = "权限管理", operateType = OperateType.OTHER)
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
    @RequiresPermissions("SystemManager:permission:delete")
    @Log(title = "权限管理", operateType = OperateType.DELETE)
    @DeleteMapping("/api/permission/{id}")
    @ResponseBody
    public Result deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return ResultUtil.success();
    }

    @ApiOperation("修改权限信息")
    @PutMapping("/api/permission")
    @RequiresPermissions("SystemManager:permission:update")
    @Log(title = "权限管理", operateType = OperateType.UPDATE)
    @ResponseBody
    public Result updatePermission(Permission permission) {
        permissionService.updatePermission(permission);
        return ResultUtil.success();
    }

    @ApiOperation("新增权限")
    @Log(title = "权限管理", operateType = OperateType.INSERT)
    @RequiresPermissions("SystemManager:permission:insert")
    @PostMapping("/api/permission")
    @ResponseBody
    public Result insertPermission(Permission permission) {
        permissionService.insertPermission(permission);
        return ResultUtil.success();
    }


}
