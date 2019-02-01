package com.dimple.modules.BackStageModule.SystemManager.controller;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import com.dimple.utils.IpUtil;
import com.dimple.utils.ShiroUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @className: UserProfileController
 * @description: 个人信息维护
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Controller
public class UserProfileController {

    //用户信息维护页面
    @ApiIgnore
    @GetMapping("/system/user/profile.html")
    public String userProfile(Model model) {
        User user = ShiroUtil.getUser();
        model.addAttribute("user", user);
        model.addAttribute("ip", IpUtil.getAccessIp());
        model.addAttribute("userType", "bianxiaofeng@sohu.com".equals(user.getEmail()) ? "Administrator" : "Visitor");
        return "system/user/profile/profile";
    }


}
