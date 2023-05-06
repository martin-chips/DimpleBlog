package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.config.BlogGlobalConfig;
import com.dimple.blog.front.service.service.BlogRestConfigService;
import com.dimple.blog.front.web.controller.vo.config.BlogGlobalConfigVO;
import com.dimple.common.core.utils.bean.BeanMapper;
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
public class ConfigRest extends BaseController {
    @Autowired
    private BlogRestConfigService blogRestConfigService;

    @GetMapping()
    public AjaxResult getGlobalConfig() {
        BlogGlobalConfig blogGlobalConfigBO = blogRestConfigService.getBlogGlobalConfig();
        return success(BeanMapper.convert(blogGlobalConfigBO, BlogGlobalConfigVO.class));
    }

}
