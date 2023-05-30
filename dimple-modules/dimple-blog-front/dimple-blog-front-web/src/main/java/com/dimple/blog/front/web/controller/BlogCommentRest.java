package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.front.service.service.BlogRestCommentService;
import com.dimple.blog.front.web.controller.vo.BlogCommentVO;
import com.dimple.blog.front.web.controller.vo.params.BlogCommentVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/comment")
public class BlogCommentRest extends BaseController {
    @Autowired
    private BlogRestCommentService blogRestCommentService;

    @GetMapping("/list")
    public TableDataInfo list(BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogRestCommentService.selectBlogCommentListWithSub(blogCommentBO);
        return getDataTable(BeanMapper.convertList(list, BlogCommentVO.class));
    }

    @PostMapping
    @VisitLog(title = VisitLogTitle.ADD_COMMENT, pageId = "#blogComment.articleId")
    public AjaxResult add(@RequestBody @Validated BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        return toAjax(blogRestCommentService.insertBlogComment(blogCommentBO));
    }

    @PostMapping("likeCount/{id}")
    @VisitLog(title = VisitLogTitle.LIKE_COMMENT, pageId = "#id")
    public AjaxResult like(@PathVariable Long id) {
        return toAjax(blogRestCommentService.addBlogCommentLikeCount(id));
    }

}
