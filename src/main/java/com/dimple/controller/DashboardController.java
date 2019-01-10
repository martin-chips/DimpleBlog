package com.dimple.controller;

import com.dimple.framework.enums.BlogStatus;
import com.dimple.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : DashboardController
 * @description : 登录显示的首页的地址信息
 * @date : 01/09/19 19:18
 */
@Controller
public class DashboardController {

    @Autowired
    BlogService blogService;

    @GetMapping("/page/dashboard.html")
    public String dashboardPage(Model model) {
        model.addAttribute("published", blogService.selectBlogCountByStatus(BlogStatus.PUBLISHED));
        model.addAttribute("draft", blogService.selectBlogCountByStatus(BlogStatus.DRAFT));
        model.addAttribute("dustbin", blogService.selectBlogCountByStatus(BlogStatus.DUSTBIN));
        model.addAttribute("all", blogService.selectBlogCountByStatus(BlogStatus.ALL));
        return "dashboard";
    }


}
