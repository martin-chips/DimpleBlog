package com.dimple.modules.BackStageModule.SystemManager.controller;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import com.dimple.modules.BackStageModule.SystemManager.service.RoleService;
import com.dimple.modules.BackStageModule.SystemManager.service.UserService;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @ClassName: UserController
 * @Description: 用户Controller
 * @Auther: Owenb
 * @Date: 11/26/18 20:30
 * @Version: 1.0
 */
@Controller
@Api("用户处理Controller")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/page/personInfo")
    @RequiresPermissions("page:personInfo:view")
    @ApiIgnore
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping("/page/user/{id}")
    @RequiresPermissions("page:userUpdate:view")
    @ApiIgnore
    public String userUpdatePage(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "system/user/update";
    }

    @GetMapping("/page/userAdd.html")
    @RequiresPermissions("page:userAdd:view")
    @ApiIgnore
    public String userAddPage(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "system/user/add";
    }

    @GetMapping("/page/user/password/{id}")
    @RequiresPermissions("page:userResetPassword:view")
    @ApiIgnore
    public String resetUserPassword(@PathVariable Integer id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("loginId", userById.getUserLoginId());
        model.addAttribute("userId", userById.getUserId());
        return "system/user/resetPassword";
    }

    @GetMapping("/page/user.html")
    @RequiresPermissions("page:user:view")
    @ApiIgnore
    public String userListPage() {
        return "system/user/list";
    }

    @ApiOperation("获取User信息")
    @GetMapping("/api/user")
    @RequiresPermissions("systemManager:user:query")
    @ResponseBody
    @Log(title = "用户管理", operateType = OperateType.SELECT)
    public Result getUsers(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "locked", required = false) Boolean locked,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {

        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<User> allUsers = userService.getAllUsers(phone, locked, startTime, endTime, loginId, pageable);
        return ResultUtil.success(allUsers);
    }

    @ApiOperation("删除User")
    @RequiresPermissions("systemManager:user:delete")
    @DeleteMapping("/api/user/{ids}")
    @Log(title = "用户管理", operateType = OperateType.DELETE)
    @ResponseBody
    public Result deleteUser(@PathVariable Integer ids[]) {
        Integer i = userService.deleteUser(ids);
        return ResultUtil.success(i);
    }

    @ApiOperation("新增User")
    @PostMapping("/api/user")
    @RequiresPermissions("systemManager:user:insert")

    @Log(title = "用户管理", operateType = OperateType.INSERT)
    @ResponseBody
    public Result insertUser(User user) {
        userService.insertUser(user);
        return ResultUtil.success();
    }

    @ApiOperation("修改User信息")
    @RequiresPermissions("systemManager:user:update")
    @PutMapping("/api/user")
    @Log(title = "用户管理", operateType = OperateType.UPDATE)
    @ResponseBody
    public Result updateUser(User user) {
        userService.updateUserInfo(user);
        return ResultUtil.success();
    }

    @ApiOperation("切换User的状态")
    @PutMapping("/api/user/{id}/{locked}")
    @RequiresPermissions("systemManager:userStatus:update")
    @Log(title = "用户管理", operateType = OperateType.CHANGE_STATUS)
    @ResponseBody
    public Result changeLocked(@PathVariable Integer id, @PathVariable Boolean locked) {
        userService.changeLocked(id, locked);
        return ResultUtil.success();
    }


}
