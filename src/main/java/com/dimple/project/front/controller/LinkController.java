package com.dimple.project.front.controller;

import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: LinkController
 * @description:
 * @author: Dimple
 * @date: 2019/12/15
 */
@RestController("frontLinkController")
@RequestMapping("/f")
public class LinkController extends BaseController {

    @Autowired
    LinkService linkService;

    @GetMapping("/link")
    @VLog(title = "查看友链")
    public AjaxResult link() {
        Link link = new Link();
        link.setDisplay(true);
        link.setStatus(true);
        List<Link> links = linkService.selectLinkList(link);
        return AjaxResult.success(links);
    }

    /**
     * 申请link
     */
    @PostMapping("/link")
    @VLog(title = "申请友链")
    public AjaxResult insertLink(@RequestBody Link link) {
        link.setStatus(false);
        link.setDisplay(false);
        return toAjax(linkService.insertLink(link));
    }
}
