package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.BlogArticlePrevNextBO;
import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.front.service.service.BlogRestArticleService;
import com.dimple.blog.front.service.service.BlogRestCommentService;
import com.dimple.blog.front.web.controller.vo.BlogArticlePrevNextVO;
import com.dimple.blog.front.web.controller.vo.BlogArticleVO;
import com.dimple.blog.front.web.controller.vo.BlogCommentVO;
import com.dimple.blog.front.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.PageDomain;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Blog articleController
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/article")
public class BlogArticleRest extends BaseController {
    @Autowired
    private BlogRestArticleService blogRestArticleService;
    @Autowired
    private BlogRestCommentService commentService;

    @GetMapping("/list")
    public TableDataInfo list(BlogArticleVOParams params) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(params, BlogArticleBO.class);
        List<BlogArticleBO> list;
        if (params.getTagId() != null) {
            // means this request from tag page.
            list = blogRestArticleService.selectBlogArticleListByTagId(params.getTagId());
        } else {
            list = blogRestArticleService.selectBlogArticleList(blogArticleBO);
        }
        return getDataTable(BeanMapper.convertList(list, BlogArticleVO.class));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        BlogArticleBO blogArticleBO = blogRestArticleService.selectBlogArticleById(id);
        BlogCommentBO blogCommentBO = new BlogCommentBO();
        blogCommentBO.setArticleId(id);
        startInnerPage(new PageDomain());
        List<BlogCommentBO> blogCommentBOS = commentService.selectBlogCommentListWithSub(blogCommentBO);
        blogArticleBO.setCommentCount(((Page) blogCommentBOS).getTotal());
        BlogArticleVO blogArticleVO = BeanMapper.convert(blogArticleBO, BlogArticleVO.class);
        blogArticleVO.setComments(BeanMapper.convertList(blogCommentBOS, BlogCommentVO.class));
        return success(blogArticleVO);
    }


    @GetMapping("prevNext")
    public AjaxResult getPreAndNextArticle(@RequestParam Long id) {
        BlogArticlePrevNextBO blogArticlePrevNextBO = blogRestArticleService.selectPrevNextArticle(id);
        return success(BeanMapper.convert(blogArticlePrevNextBO, BlogArticlePrevNextVO.class));
    }


    @PutMapping("/{id}/like")
    @VisitLog(title = VisitLogTitle.LIKE_ARTICLE, pageId = "#id")
    public AjaxResult likeArticle(@PathVariable Long id) {
        return success(blogRestArticleService.likeArticle(id));
    }
}
