package com.dimple.controller;

import com.dimple.service.LoginService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(String loginId, String password, Model model) throws Exception {
        Result result = loginService.login(loginId, password);
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            return "/index";
        } else {
            model.addAttribute("msg", result.getMsg());
            model.addAttribute("userName", loginId);
            return "/login";
        }
    }
}
