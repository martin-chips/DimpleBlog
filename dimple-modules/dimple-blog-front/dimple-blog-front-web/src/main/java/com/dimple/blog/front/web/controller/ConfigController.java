package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogConfigService;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController
 *
 * @author Dimple
 * @date 3/29/2023
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController {
    @Autowired
    private BlogConfigService blogConfigService;
    @GetMapping()
    public AjaxResult getInfo() {
        return success(blogConfigService.getBlogConfig());
    }

}
