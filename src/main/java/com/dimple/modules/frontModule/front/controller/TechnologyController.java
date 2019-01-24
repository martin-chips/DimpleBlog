package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.log.annotation.VLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: TechnologyController
 * @description:
 * @auther: Owenb
 * @date: 01/23/19 17:30
 * @version: 1.0
 */
@Controller
public class TechnologyController {


    @GetMapping("/technology.html")
    @VLog(title = "技术分享")
    public String technologyPage(Model model) {
//        model.addAttribute("clickBlog", frontService.getClickBlog());
//        model.addAttribute("supportBlog", frontService.getSupportBlog());
//        model.addAttribute("newestUpdateBlog", frontService.getAllBlogByPageable());

        return "front/technology";
    }
}
