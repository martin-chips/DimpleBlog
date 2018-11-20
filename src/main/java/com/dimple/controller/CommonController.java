package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: CommonController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 10:39
 * @Version: 1.0
 */
@Controller
public class CommonController {
    @RequestMapping("/login")
    public String toLogin() {
        return "/login";
    }
}
