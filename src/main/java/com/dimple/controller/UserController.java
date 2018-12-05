package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: UserController
 * @Description: 用户Controller
 * @Auther: Owenb
 * @Date: 11/26/18 20:30
 * @Version: 1.0
 */
@Controller
public class UserController {

    @RequestMapping("/personInfo")
    public String userProfile() {
        return "user/profile";
    }

}
