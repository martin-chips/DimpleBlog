package com.dimple.controller.log;

import com.dimple.framework.message.Result;
import com.dimple.service.LinksService;
import com.dimple.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Result login1(String loginId, String password) throws Exception {

        Result result = loginService.login(loginId, password);
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
