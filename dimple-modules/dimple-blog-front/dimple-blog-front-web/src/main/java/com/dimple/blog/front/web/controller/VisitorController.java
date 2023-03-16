package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.VisitorService;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VisitorController
 *
 * @author Dimple
 * @date 2023/3/14 12:07
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController extends BaseController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/github/code/{code}")
    @VisitLog(title = VisitLogTitle.GITHUB_LOGIN)
    public AjaxResult getGithubInfo(@PathVariable String code) {
        return success(visitorService.getGithubVisitorInfo(code));
    }

}
