package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogArchivesService;
import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.BlogCategoryService;
import com.dimple.blog.front.service.service.BlogCommentService;
import com.dimple.blog.front.service.service.BlogTagService;
import com.dimple.blog.front.service.service.bo.BlogArchivesBO;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogCategoryBO;
import com.dimple.blog.front.service.service.bo.BlogCommentBO;
import com.dimple.blog.front.service.service.bo.BlogTagBO;
import com.dimple.blog.front.web.controller.vo.BlogArchivesVO;
import com.dimple.blog.front.web.controller.vo.BlogArticleVO;
import com.dimple.blog.front.web.controller.vo.BlogCategoryVO;
import com.dimple.blog.front.web.controller.vo.BlogCommentVO;
import com.dimple.blog.front.web.controller.vo.BlogTagVO;
import com.dimple.blog.front.web.controller.vo.params.BlogArchivesVOParams;
import com.dimple.blog.front.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.blog.front.web.controller.vo.params.BlogCategoryVOParams;
import com.dimple.blog.front.web.controller.vo.params.BlogCommentVOParams;
import com.dimple.blog.front.web.controller.vo.params.BlogTagVOParams;
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
 * @author BianXiaofeng
 * @date 2023/3/16
 */
@RestController
@RequestMapping("/panel")
public class BlogPanelController extends BaseController {
    @Autowired
    private BlogArchivesService archivesService;
    @Autowired
    private BlogArticleService blogArticleService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogTagService blogTagService;


    @GetMapping("archives")
    public TableDataInfo getArchiveList(BlogArchivesVOParams params) {
        startPage();
        List<BlogArchivesBO> archives = archivesService.getArchiveList(params.getCountType());
        return getDataTable(BeanMapper.convertList(archives, BlogArchivesVO.class));
    }

    @GetMapping("/category")
    public TableDataInfo list(BlogCategoryVOParams blogCategory) {
        startPage();
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        List<BlogCategoryBO> list = blogCategoryService.selectBlogCategoryList(blogCategoryBO);
        List<BlogCategoryVO> blogCategoryVOS = BeanMapper.convertList(list, BlogCategoryVO.class);
        return getDataTable(blogCategoryVOS);
    }

    @GetMapping("/comment")
    public TableDataInfo list(BlogCommentVOParams blogComment) {
        startPage();
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogCommentService.selectBlogCommentListWithSub(blogCommentBO);
        return getDataTable(BeanMapper.convertList(list, BlogCommentVO.class));
    }

    @GetMapping("/tag")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        startPage();
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @GetMapping("/article")
    public TableDataInfo list(BlogArticleVOParams params) {
        startPage();
        BlogArticleBO blogArticleBO = BeanMapper.convert(params, BlogArticleBO.class);
        List<BlogArticleBO> list = blogArticleService.selectBlogArticleList(blogArticleBO);
        return getDataTable(BeanMapper.convertList(list, BlogArticleVO.class));
    }
}
