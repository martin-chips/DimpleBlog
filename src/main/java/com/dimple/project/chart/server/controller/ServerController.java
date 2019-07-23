package com.dimple.project.chart.server.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.project.chart.server.domain.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: ServerController
 * @description: 服务器监控
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Controller
@RequestMapping("/chart/server")
public class ServerController extends BaseController {
    private String prefix = "chart/server";

    @RequiresPermissions("chart:server:view")
    @GetMapping()
    public String server(Model model) throws Exception {
        Server server = new Server();
        server.copyTo();
        model.addAttribute("server", server);
        return prefix + "/server";
    }
}
