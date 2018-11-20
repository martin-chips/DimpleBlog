package com.dimple.controller;

import com.dimple.service.LoginService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import com.dimple.utils.message.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName: HomeController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 19:37
 * @Version: 1.0
 */
@Controller
public class HomeController {
    @Autowired
    LoginService loginService;


    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "/user/403";
    }

//    @ResponseBody
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(String username, String password, Model model) throws Exception {
//        System.out.println("login()");
//        Result result = loginService.login(username, password);
//        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
//            return "/index";
//        } else {
//            model.addAttribute("msg", result.getMsg());
//            model.addAttribute("userName", username);
//            return "/user/login";
//        }
//    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        loginService.logout();
        return "/user/login";
    }

}
