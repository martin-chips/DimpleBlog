package com.dimple.blog.web.controller;

import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.service.service.BlogArticleService;
import com.dimple.blog.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * BlogAboutMeController
 *
 * @author Dimple
 * @date 3/24/2023
 */
@RestController
@RequestMapping("/about")
public class BlogAboutMeController extends BaseController {
    private static final Long ABOUT_ME_PAGE_ID = -2000L;
    @Autowired
    private BlogArticleService blogArticleService;

    @RequiresPermissions("blog:about:query")
    @GetMapping()
    public AjaxResult getInfo() {
        return success(blogArticleService.selectBlogArticleDetailById(ABOUT_ME_PAGE_ID));
    }


    @RequiresPermissions("blog:about:edit")
    @OperationLog(title = "关于我", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody BlogArticleVOParams blogArticle) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        blogArticleBO.setId(ABOUT_ME_PAGE_ID);
        return toAjax(blogArticleService.updateBlogArticle(blogArticleBO));
    }
}
