package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.*;
import com.dimple.blog.front.service.service.*;
import com.dimple.blog.front.web.controller.vo.*;
import com.dimple.blog.front.web.controller.vo.params.*;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BlogPanelController
 *
 * @author Dimple
 * @date 2023/3/16
 */
@RestController
@RequestMapping("/panel")
public class BlogPanelRest extends BaseController {
    @Autowired
    private BlogRestArchivesService archivesService;
    @Autowired
    private BlogRestArticleService blogRestArticleService;
    @Autowired
    private BlogRestCategoryService blogRestCategoryService;
    @Autowired
    private BlogRestCommentService blogRestCommentService;
    @Autowired
    private BlogRestTagService blogRestTagService;


    @GetMapping("archives")
    public TableDataInfo getArchiveList(BlogArchivesVOParams params) {
        List<BlogArchivesBO> archives = archivesService.getArchiveList(params.getCountType());
        return getDataTable(BeanMapper.convertList(archives, BlogArchivesVO.class));
    }

    @GetMapping("/category")
    public TableDataInfo list(BlogCategoryVOParams blogCategory) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        List<BlogCategoryBO> list = blogRestCategoryService.selectBlogCategoryList(blogCategoryBO);
        List<BlogCategoryVO> blogCategoryVOS = BeanMapper.convertList(list, BlogCategoryVO.class);
        return getDataTable(blogCategoryVOS);
    }

    @GetMapping("/comment")
    public TableDataInfo list(BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogRestCommentService.selectBlogCommentListWithSub(blogCommentBO);
        return getDataTable(BeanMapper.convertList(list, BlogCommentVO.class));
    }

    @GetMapping("/tag")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogRestTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @GetMapping("/article")
    public TableDataInfo list(BlogArticleVOParams params) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(params, BlogArticleBO.class);
        List<BlogArticleBO> list = blogRestArticleService.selectBlogArticleList(blogArticleBO);
        return getDataTable(BeanMapper.convertList(list, BlogArticleVO.class));
    }
}
