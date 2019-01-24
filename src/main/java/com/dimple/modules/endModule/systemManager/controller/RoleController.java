package com.dimple.modules.endModule.systemManager.controller;

import com.dimple.modules.endModule.systemManager.bean.Role;
import com.dimple.modules.endModule.systemManager.service.RoleService;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @ClassName: RoleController
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/8 12:40
 * @Version: 1.0
 */
@Controller
@Api("角色处理Controller")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/page/role.html", method = RequestMethod.GET)
    @ApiIgnore
    public String rolePage() {
        return "system/role/list";
    }

    @GetMapping("/page/role/{id}.html")
    @ApiIgnore
    public String roleUpdatePage(@PathVariable Integer id, Model model) {
        Role byUserId = roleService.getRoleByRoleId(id);
        model.addAttribute("role", byUserId);
        return "system/role/update";
    }

    @GetMapping("/page/roleAdd.html")
    @ApiIgnore
    public String roleAdd(Model model) {
        return "system/role/add";
    }

    @ApiOperation("获取角色信息")
    @GetMapping("/api/role")
    @Log(title = "角色管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result roleList(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
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
    @Log(title = "角色管理", operateType = OperateType.UPDATE)
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
    @Log(title = "角色管理", operateType = OperateType.DELETE)
    @DeleteMapping("/api/role/{ids}")
    @ResponseBody
    public Result deleteRole(@PathVariable Integer ids[]) {
        int i = roleService.deleteRole(ids);
        return ResultUtil.success(i);
    }

    @ApiOperation("新增角色")
    @PostMapping("/api/role")
    @Log(title = "角色管理", operateType = OperateType.INSERT)
    @ResponseBody
    public Result insertRole(Role role) {
        roleService.insertRole(role);
        return ResultUtil.success();
    }

    @ApiOperation("更改角色的状态")
    @PutMapping("/api/role/{id}/{locked}")
    @Log(title = "角色管理", operateType = OperateType.CHANGE_STATUS)
    @ResponseBody
    public Result changeLocked(@PathVariable Integer id, @PathVariable Boolean locked) {
        roleService.changeRoleLocked(id, locked);
        return ResultUtil.success();
    }
}
