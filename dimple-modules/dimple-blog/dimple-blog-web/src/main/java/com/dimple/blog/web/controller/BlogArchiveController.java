package com.dimple.blog.web.controller;


import com.dimple.blog.service.service.BlogArchiveService;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.security.annotation.InnerAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BlogArchiveController
 *
 * @author Dimple
 * @date 2023-05-05
 */
@RestController
@RequestMapping("/archive")
public class BlogArchiveController extends BaseController {
    @Autowired
    private BlogArchiveService blogArchiveService;

    @InnerAuth
    @GetMapping(value = "/inner/archives")
    AjaxResult getArchives() {
        return success(blogArchiveService.getArchives());
    }

    @InnerAuth
    @GetMapping(value = "/inner/list")
    AjaxResult getArchiveList(String countType) {
        return success(blogArchiveService.getArchiveList(countType));
    }

}
