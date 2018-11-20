package com.dimple.controller;

import com.dimple.service.LoginService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import com.dimple.utils.message.ResultUtil;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 登录的Controller
     *
     * @param loginId
     * @param password
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(String loginId, String password, Model model) throws Exception {
        if (StringUtils.isBlank(loginId) || StringUtils.isBlank(password)) {
            model.addAttribute(ResultUtil.error(ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getCode(), ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getMsg()));
            return "redirect:/login";
        }
        Result result = loginService.login(loginId, password);
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            return "redirect:/index";
        } else {
            model.addAttribute(ResultUtil.error(result.getCode(), result.getMsg()));
            return "redirect:/login";
        }
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        loginService.logout();
        return "/login";
    }
}
