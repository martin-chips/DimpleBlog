package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogRestVisitorService;
import com.dimple.blog.front.web.controller.vo.params.VisitLogVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.RequestType;
import com.dimple.common.log.enums.VisitLogTitle;
import com.dimple.common.log.service.AsyncLogService;
import com.dimple.system.api.model.BlogVisitLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * VisitorController
 *
 * @author Dimple
 * @date 2023/3/14 12:07
 */
@RestController
@Slf4j
@RequestMapping("/visitor")
public class VisitorRest extends BaseController {

    @Autowired
    private BlogRestVisitorService blogRestVisitorService;
    @Autowired
    private AsyncLogService asyncLogService;

    @GetMapping("/github/code/{code}")
    @VisitLog(title = VisitLogTitle.GITHUB_LOGIN)
    public AjaxResult getGithubInfo(@PathVariable String code) {
        return success(blogRestVisitorService.getGithubVisitorInfo(code));
    }

    @PostMapping("log")
    public AjaxResult saveVisitLog(@RequestBody VisitLogVOParams params) {
        BlogVisitLogBO blogVisitLogBO = BeanMapper.convert(params, BlogVisitLogBO.class);
        blogVisitLogBO.setRequestType(RequestType.PAGE_REQUEST.getCode());
        asyncLogService.saveVisitLog(blogVisitLogBO);
        return success();
    }
}
