package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogCommentService;
import com.dimple.blog.front.service.service.bo.BlogCommentBO;
import com.dimple.blog.front.web.controller.vo.BlogCommentVO;
import com.dimple.blog.front.web.controller.vo.params.BlogCommentVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/comment")
public class BlogCommentController extends BaseController {
    @Autowired
    private BlogCommentService blogCommentService;

    @GetMapping("/list")
    public TableDataInfo list(BlogCommentVOParams blogComment) {
        startPage();
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogCommentService.selectBlogCommentListWithSub(blogCommentBO);
        return getDataTable(BeanMapper.convertList(list, BlogCommentVO.class));
    }


    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogCommentService.selectBlogCommentById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody @Validated BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        return toAjax(blogCommentService.insertBlogComment(blogCommentBO));
    }

    @PostMapping("likeCount/{id}")
    public AjaxResult like(@PathVariable Long id) {
        return toAjax(blogCommentService.addBlogCommentLikeCount(id));
    }

}
