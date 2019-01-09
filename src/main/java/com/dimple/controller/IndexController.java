package com.dimple.controller;

import com.dimple.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : IndexController
 * @description : 登录显示的首页的地址信息
 * @date : 01/09/19 19:18
 */
@Controller
public class IndexController {

    @Autowired
    LinksService linksService;

    @RequestMapping({"/page/index.html"})
    public String indexPage(Model model) {
        model.addAttribute("unhandledLinksCount", linksService.getUnHandledLinksCount());
        return "index";
    }

    @RequestMapping("/page/dashboard.html")
    public String dashboardPage() {
        return "dashboard";
    }


}
