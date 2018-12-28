package com.dimple.controller;

import com.dimple.bean.Role;
import com.dimple.bean.User;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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

    @RequestMapping("/page/role.html")
    public String rolePage() {
        return "system/role/list";
    }

    @GetMapping("/page/role/{id}.html")
    public String roleUpdatePage(@PathVariable Integer id, Model model) {
        Role byUserId = roleService.getRoleByRoleId(id);
        model.addAttribute("role", byUserId);
        return "system/role/update";
    }

    @GetMapping("/page/roleAdd.html")
    public String roleAdd() {
        return "system/role/add";
    }

    @ApiOperation("获取角色信息")
    @GetMapping("/api/role")
    @ResponseBody
    public Result roleList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "locked", required = false) Boolean locked,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Role> allRoles = roleService.getAllRoles(roleName, description, locked, startTime, endTime, pageable);
        return ResultUtil.success(allRoles);
    }

    @ApiOperation("修改角色信息")
    @PutMapping("/api/role")
    @ResponseBody
    public Result updateRole(Integer roleId, String roleName, String description, Boolean locked, Integer permissionIds[]) {
        Role role = new Role();
        role.setLocked(locked);
        role.setRoleId(roleId);
        role.setDescription(description);
        role.setRoleName(roleName);
        roleService.updateRole(role, permissionIds);
        return ResultUtil.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/api/role/{ids}")
    @ResponseBody
    public Result deleteRole(@PathVariable Integer ids[]) {
        int i = roleService.deleteRole(ids);
        return ResultUtil.success(i);
    }

    @ApiOperation("新增角色")
    @PostMapping("/api/role")
    @ResponseBody
    public Result insertRole(Role role) {
        roleService.insertRole(role);
        return ResultUtil.success();
    }

    @ApiOperation("更改角色的状态")
    @PutMapping("/api/role/{id}/{locked}")
    @ResponseBody
    public Result changeLocked(@PathVariable Integer id, @PathVariable Boolean locked) {
        roleService.changeRoleLocked(id, locked);
        return ResultUtil.success();
    }
}
