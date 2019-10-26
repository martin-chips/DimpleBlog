package com.dimple.project.monitor.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ServerController
 * @description: 服务器监控
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController {
    @PreAuthorize("@permissionService.hasPermission('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
