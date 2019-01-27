package com.dimple.modules.frontModule.front.controller;

import com.dimple.modules.endModule.blogManager.service.BlogService;
import com.dimple.modules.frontModule.front.domain.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @className: ArchiveController
 * @description: 归档的Controller
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */
@Controller
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archive.html")
    public String archivePage(Model model) {
        List<Archive> archives = blogService.getArchive();
        model.addAttribute("archives", archives);
        return "front/archives";
    }

}
