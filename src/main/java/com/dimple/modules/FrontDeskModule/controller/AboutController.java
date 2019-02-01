package com.dimple.modules.FrontDeskModule.controller;

import com.dimple.framework.log.annotation.VLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: AboutController
 * @description: 关于我
 * @auther: Owenb
 * @date: 01/23/19 17:21
 * @version: 1.0
 */
@Controller
public class AboutController {

    @GetMapping("/about.html")
    @VLog(title = "关于我")
    public String aboutPage() {
        return "front/about";
    }
}
