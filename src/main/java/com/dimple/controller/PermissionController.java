package com.dimple.controller;

import com.dimple.bean.Permission;
import com.dimple.bean.Role;
import com.dimple.service.PermissionService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
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
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/system/permission/permission.html")
    public String permissionPage() {
        return "system/permission/permission";
    }

    @GetMapping("/system/permission/add/{id}.html")
    public String permissionAddPage(@PathVariable Integer id, Model model) {
        model.addAttribute("pTitle", permissionService.getPermissionPName(id));
        model.addAttribute("pId", id);
        return "system/permission/add";
    }

    @GetMapping("/system/permission/update/{id}.html")
    public String permissionUpdatePage(@PathVariable Integer id, Model model) {
        Permission permission = permissionService.getPermissionById(id);
        String pName = permissionService.getPermissionPName(id);
        model.addAttribute("permission", permission);
        model.addAttribute("pTitle", permissionService.getPermissionPName(id));
        return "system/permission/update";
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

    /**
     * 获取permission的表的数据
     *
     * @return
     */
    @GetMapping("/system/permission.json")
    @ResponseBody
    public Result getPermissionList(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "status", required = false) Boolean status,
                                    @RequestParam(value = "type", required = false) Integer type) {
        List<Permission> permissions = permissionService.getPermissionAll(title, type,status);
        return ResultUtil.success(permissions);
    }

    /**
     * 为删除的时候做铺垫，如果先进行关联查询，然后返回关联查询结果
     *
     * @param permissionId
     * @return
     */
    @DeleteMapping("/system/permission/{id}/validate")
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

    @DeleteMapping("/system/permission/{id}")
    @ResponseBody
    public Result deletePermission(@PathVariable Integer id) {
        //todo 删除的时候需要判断是否有关联信息，如果有关联信息，需要进行反馈
        int i = permissionService.deletePermission(id);
        return ResultUtil.success(i);
    }

    @PutMapping("/system/permission")
    @ResponseBody
    public Result updatePermission(Permission permission) {
        int i = permissionService.updatePermission(permission);
        return ResultUtil.success();
    }

    @PostMapping("/system/permission")
    @ResponseBody
    public Result insertPermission(Permission permission) {
        Integer integer = permissionService.insertPermission(permission);
        return ResultUtil.success(integer);
    }
}
