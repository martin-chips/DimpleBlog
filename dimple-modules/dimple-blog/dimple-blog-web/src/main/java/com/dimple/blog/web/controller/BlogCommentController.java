package com.dimple.blog.web.controller;

import com.dimple.blog.service.service.BlogCommentService;
import com.dimple.blog.service.service.bo.BlogCommentBO;
import com.dimple.blog.web.controller.vo.BlogCommentVO;
import com.dimple.blog.web.controller.vo.params.BlogCommentVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    @RequiresPermissions("blog:comment:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogCommentVOParams blogComment) {
        startPage();
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogCommentService.selectBlogCommentList(blogCommentBO);
        return getDataTable(BeanMapper.convertList(list, BlogCommentVO.class));
    }

    @RequiresPermissions("blog:comment:export")
    @OperationLog(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        List<BlogCommentBO> list = blogCommentService.selectBlogCommentList(blogCommentBO);
        ExcelUtil<BlogCommentBO> util = new ExcelUtil<>(BlogCommentBO.class);
        util.exportExcel(response, list, "评论数据");
    }

    @RequiresPermissions("blog:comment:query")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        BlogCommentBO blogCommentBO = blogCommentService.selectBlogCommentById(id);
        return success(BeanMapper.convert(blogCommentBO, BlogCommentVO.class));
    }

    @RequiresPermissions("blog:comment:edit")
    @OperationLog(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}")
    public AjaxResult edit(@PathVariable Long id, @RequestBody BlogCommentVOParams blogComment) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogComment, BlogCommentBO.class);
        blogCommentBO.setId(id);
        return toAjax(blogCommentService.updateBlogComment(blogCommentBO));
    }

    @RequiresPermissions("blog:comment:remove")
    @OperationLog(title = "评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blogCommentService.deleteBlogCommentByIds(ids));
    }
}
