package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.log.annotation.VLog;
import com.dimple.modules.frontModule.front.service.IndexService;
import com.dimple.modules.frontModule.front.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className: TechnologyController
 * @description:
 * @auther: Owenb
 * @date: 01/23/19 17:30
 * @version: 1.0
 */
@Controller
public class TechnologyController {

    @Autowired
    IndexService indexService;

    @Autowired
    TechnologyService technologyService;

    @GetMapping("/technology.html")
    @VLog(title = "技术分享")
    public String technologyPage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum <= 0 ? 0 : pageNum, pageSize <= 0 ? 10 : pageSize, Sort.Direction.DESC, "createTime");
        model.addAttribute("newestBlog", technologyService.getNewestBlog(pageable));
        return "front/technology";
    }


}
