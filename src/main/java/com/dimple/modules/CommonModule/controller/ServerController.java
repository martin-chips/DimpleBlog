package com.dimple.modules.CommonModule.controller;

import com.dimple.modules.CommonModule.bean.server.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ServerController
 * @Description: 服务器信息
 * @Auther: Owenb
 * @Date: 12/07/18 10:27
 * @Version: 1.0
 */
@Controller
public class ServerController {

    @RequestMapping("/server/serverInfo")
    public String serverInfo(Model model) throws Exception {
        Server server = new Server();
        server.copyTo();
        model.addAttribute("server", server);
        return "server/serverInfo";
    }

}
