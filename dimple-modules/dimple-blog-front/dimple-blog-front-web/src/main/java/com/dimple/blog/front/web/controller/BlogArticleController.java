package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.web.controller.vo.BlogArticleVO;
import com.dimple.blog.front.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Blog articleController
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/article")
public class BlogArticleController extends BaseController {
    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping("/list")
    public TableDataInfo list(BlogArticleVOParams blogArticle) {
        startPage();
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        List<BlogArticleBO> list = blogArticleService.selectBlogArticleList(blogArticleBO);
        return getDataTable(BeanMapper.convertList(list, BlogArticleVO.class));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogArticleService.selectBlogArticleById(id));
    }


    @GetMapping("prevNext")
    public AjaxResult getPreAndNextArticle() {
        return success();
    }


    @GetMapping("latest")
    public TableDataInfo getLatestArticle(BlogArticleVOParams blogArticle) {
        blogArticle.getParams();
        startPage();
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        List<BlogArticleBO> list = blogArticleService.selectBlogArticleList(blogArticleBO);
        return getDataTable(BeanMapper.convertList(list, BlogArticleBO.class));
    }
}
