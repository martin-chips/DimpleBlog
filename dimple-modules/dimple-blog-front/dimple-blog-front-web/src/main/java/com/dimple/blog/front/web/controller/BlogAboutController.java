package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.web.controller.vo.BlogArticleVO;
import com.dimple.common.core.enums.BlogPageId;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BlogAboutController
 *
 * @author Dimple
 * @date 2023/3/16
 */
@RestController
@RequestMapping("/about")
public class BlogAboutController extends BaseController {
    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping
    @VisitLog(title = VisitLogTitle.LIST_ABOUT)
    public AjaxResult getAbout() {
        BlogArticleBO blogArticleBO = blogArticleService.selectBlogArticleDetailById(BlogPageId.ABOUT.getId());
        return success(BeanMapper.convert(blogArticleBO, BlogArticleVO.class));
    }

}
