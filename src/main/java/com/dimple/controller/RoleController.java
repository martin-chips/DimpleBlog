package com.dimple.controller;

import com.dimple.bean.Role;
import com.dimple.service.RoleService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RoleController
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/8 12:40
 * @Version: 1.0
 */
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/system/role.html")
    public String rolePage() {
        return "system/role/list";
    }

    @GetMapping("/system/role/{id}")
    public String roleUpdatePage(@PathVariable Integer id, Model model) {
        Role byUserId = roleService.getRoleByRoleId(id);
        model.addAttribute("role", byUserId);
        return "system/role/update";
    }

    @GetMapping("/system/role/add.html")
    public String roleAdd() {
        return "system/role/add";
    }

    @GetMapping("/system/role.json")
    @ResponseBody
    public Result roleList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "locked", required = false) Boolean locked,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Role> roleList = roleService.getAllRoles(roleName, description, locked, startTime, endTime);
        PageInfo pageInfo = new PageInfo(roleList);
        return ResultUtil.success(pageInfo);
    }

    @PutMapping("/system/role")
    @ResponseBody
    public Result updateRole(Integer roleId, String roleName, String description, Boolean locked, Integer permissionIds[]) {
        Role role = new Role();
        role.setLocked(locked);
        role.setRoleId(roleId);
        role.setDescription(description);
        role.setRoleName(roleName);
        int i = roleService.updateRole(role,permissionIds);
        return ResultUtil.success(i);
    }

    @DeleteMapping("/system/role/{ids}")
    @ResponseBody
    public Result deleteRole(@PathVariable Integer ids[]) {
        int i = roleService.deleteRole(ids);
        return ResultUtil.success(i);
    }

    @PostMapping("/system/role")
    @ResponseBody
    public Result insertRole(Role role) {
        int i = roleService.insertRole(role);
        return ResultUtil.success(i);
    }

    @PutMapping("/system/role/{id}/{locked}")
    @ResponseBody
    public Result changeLocked(@PathVariable Integer id, @PathVariable Boolean locked) {
        Integer i = roleService.changeRoleLocked(id, locked);
        return ResultUtil.success(i);
    }
}
