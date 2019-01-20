package com.dimple.modules.common.controller;

import com.dimple.framework.message.Result;
import com.dimple.modules.linkManager.service.LinksService;
import com.dimple.modules.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 11:22
 * @Version: 1.0
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    LinksService linksService;


    @RequestMapping({"/page/index.html"})
    public String indexPage(Model model) {
        model.addAttribute("unhandledLinksCount", linksService.getUnHandledLinksCount());
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Result login1(String loginId, String password, Boolean rememberMe) throws Exception {

        Result result = loginService.login(loginId, password,rememberMe);
        return result;
    }

    @RequestMapping("/page/login.html")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/logout.html")
    public String logout(HttpSession session) {
        loginService.logout();
        return "login";
    }


}
