package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogArticlePrevNextBO;
import com.dimple.blog.front.web.controller.vo.BlogArticlePrevNextVO;
import com.dimple.blog.front.web.controller.vo.BlogArticleVO;
import com.dimple.blog.front.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @VisitLog(title = "文章",pageId = "#id")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        BlogArticleBO blogArticleBO = blogArticleService.selectBlogArticleById(id);
        return success(BeanMapper.convert(blogArticleBO, BlogArticleVO.class));
    }


    @GetMapping("prevNext")
    public AjaxResult getPreAndNextArticle(@RequestParam Long id) {
        BlogArticlePrevNextBO blogArticlePrevNextBO = blogArticleService.selectPrevNextArticle(id);
        return success(BeanMapper.convert(blogArticlePrevNextBO, BlogArticlePrevNextVO.class));
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
