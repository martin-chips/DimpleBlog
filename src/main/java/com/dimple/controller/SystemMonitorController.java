package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: SystemMonitorController
 * @Description: 系统监控Controller
 * @Auther: Owenb
 * @Date: 12/07/18 14:13
 * @Version: 1.0
 */
@Controller
public class SystemMonitorController {
    /**
     * SwaggerUI界面跳转
     *
     * @return
     */
    @RequestMapping("/swagger.html")
    public String swaggerPage() {
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping("/druid.html")
    public String druidPage() {
        return "redirect:druid/index.html";
    }
}
