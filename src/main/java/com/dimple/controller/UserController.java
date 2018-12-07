package com.dimple.controller;

import com.dimple.bean.User;
import com.dimple.service.UserService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @RequestMapping("/personInfo")
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping("/system/user/{id}")
    public String userUpdatePage(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "system/user/update";
    }

    @GetMapping("/system/user/add.html")
    public String userAddPage() {
        return "system/user/add";
    }

    @GetMapping("/system/user/password/{id}")
    public String resetUserPassword(@PathVariable Integer id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("loginId", userById.getUserLoginId());
        model.addAttribute("userId", userById.getUserId());
        return "system/user/resetPassword";
    }

    @GetMapping("/system/user.html")
    public String userListPage() {
        return "system/user/list";
    }

    @GetMapping("/system/user.json")
    @ResponseBody
    public Result getUsers(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "status", required = false) Boolean locked,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {

        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<User> users = userService.getAllUsers(phone, locked, startTime, endTime, loginId);
        PageInfo pageInfo = new PageInfo(users);
        return ResultUtil.success(pageInfo);
    }

    @DeleteMapping("/system/user/{ids}")
    @ResponseBody
    public Result deleteUser(@PathVariable Integer ids[]) {
        Integer i = userService.deleteUser(ids);
        return ResultUtil.success(i);
    }

    @PostMapping("/system/user")
    @ResponseBody
    public Result insertUser(User user) {
        Integer i = userService.insertUser(user);
        return ResultUtil.success(i);
    }

    @PutMapping("/system/user")
    @ResponseBody
    public Result updateUser(User user) {
        Integer i = userService.updateUserInfo(user);
        return ResultUtil.success(i);
    }


}
