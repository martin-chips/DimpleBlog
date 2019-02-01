package com.dimple.modules.FrontDeskModule.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.BlogManager.service.CategoryService;
import com.dimple.modules.BackStageModule.LinkManager.bean.Link;
import com.dimple.modules.BackStageModule.LinkManager.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: LinkApplyController
 * @description: 友链申请的Controller
 * @auther: Owenb
 * @date: 01/21/19 16:16
 * @version: 1.0
 */
@Controller("frontLinkController")
public class LinkController {

    @Autowired
    LinksService linksService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/link.html")
    public String linkApplyPage(Model model) {
        model.addAttribute("links", linksService.getAllLinksHandled(null, null, null, null, PageRequest.of(0, 100, Sort.Direction.DESC, "weight")));
        return "front/link";
    }


    @GetMapping("/public/api/link")
    @ResponseBody
    public Result getLinks() {
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "weight");
        Page<Link> allLinksHandled = linksService.getAllLinksHandled(null, null, null, null, pageable);
        return ResultUtil.success(allLinksHandled);
    }


}
