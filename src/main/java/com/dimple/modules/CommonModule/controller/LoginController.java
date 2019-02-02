package com.dimple.modules.CommonModule.controller;

import com.dimple.framework.exception.BaseException;
import com.dimple.framework.message.Result;
import com.dimple.modules.CommonModule.service.LoginService;
import com.dimple.modules.BackStageModule.LinkManager.service.LinksService;
import com.dimple.utils.ServletUtil;
import com.dimple.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        model.addAttribute("user", ShiroUtil.getUser());
        model.addAttribute("unhandledLinksCount", linksService.getUnHandledLinksCount());
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Result login1(String loginId, String password, Boolean rememberMe) throws Exception {

        Result result = loginService.login(loginId, password, rememberMe);
        return result;
    }

    @GetMapping("/page/login.html")
    public Object toLogin(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtil.isAjaxRequest(request)) {
            //todo 异常处理
            throw new BaseException("登录", "user.not.exists", null, "异常");
        }
        return "login";
    }

    @GetMapping("/logout.html")
    public String logout(HttpSession session) {
        loginService.logout();
        return "login";
    }


}
