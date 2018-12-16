package com.dimple.controller;

import com.dimple.bean.User;
import com.dimple.service.UserService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 用户Controller
 * @Auther: Owenb
 * @Date: 11/26/18 20:30
 * @Version: 1.0
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/page/personInfo")
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping("/page/user/{id}")
    public String userUpdatePage(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "system/user/update";
    }

    @GetMapping("/page/userAdd.html")
    public String userAddPage() {
        return "system/user/add";
    }

    @GetMapping("/page/user/password/{id}")
    public String resetUserPassword(@PathVariable Integer id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("loginId", userById.getUserLoginId());
        model.addAttribute("userId", userById.getUserId());
        return "system/user/resetPassword";
    }

    @GetMapping("/page/user.html")
    public String userListPage() {
        return "system/user/list";
    }

    @ApiOperation("获取User信息")
    @GetMapping("/api/user")
    @ResponseBody
    public Result getUsers(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "locked", required = false) Boolean locked,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {

        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<User> users = userService.getAllUsers(phone, locked, startTime, endTime, loginId);
        PageInfo pageInfo = new PageInfo(users);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("删除User")
    @DeleteMapping("/api/user/{ids}")
    @ResponseBody
    public Result deleteUser(@PathVariable Integer ids[]) {
        Integer i = userService.deleteUser(ids);
        return ResultUtil.success(i);
    }

    @ApiOperation("新增User")
    @PostMapping("/api/user")
    @ResponseBody
    public Result insertUser(User user) {
        Integer i = userService.insertUser(user);
        return ResultUtil.success(i);
    }

    @ApiOperation("修改User信息")
    @PutMapping("/api/user")
    @ResponseBody
    public Result updateUser(User user) {
        Integer i = userService.updateUserInfo(user);
        return ResultUtil.success(i);
    }

    @ApiOperation("切换User的状态")
    @PutMapping("/api/user/{id}/{locked}")
    @ResponseBody
    public Result changeLocked(@PathVariable Integer id, @PathVariable Boolean locked) {
        Integer i = userService.changeLocked(id, locked);
        return ResultUtil.success(i);
    }


}
