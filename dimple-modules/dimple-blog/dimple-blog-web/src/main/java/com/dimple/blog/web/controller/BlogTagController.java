package com.dimple.blog.web.controller;

import com.dimple.blog.service.service.BlogTagService;
import com.dimple.blog.service.service.bo.BlogTagBO;
import com.dimple.blog.web.controller.vo.BlogTagVO;
import com.dimple.blog.web.controller.vo.params.BlogTagVOParams;
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
@RequestMapping("/tag")
public class BlogTagController extends BaseController {
    @Autowired
    private BlogTagService blogTagService;

    @RequiresPermissions("blog:tag:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        startPage();
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @RequiresPermissions("blog:tag:export")
    @OperationLog(title = "Blog Tag", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogTagVOParams blogTag) {
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        ExcelUtil<BlogTagBO> util = new ExcelUtil<>(BlogTagBO.class);
        util.exportExcel(response, list, "Blog Tag 数据");
    }

    @RequiresPermissions("blog:tag:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogTagService.selectBlogTagById(id));
    }

    @RequiresPermissions("blog:tag:add")
    @OperationLog(title = "Blog Tag", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogTagVOParams blogTag) {
        return toAjax(blogTagService.insertBlogTag(BeanMapper.convert(blogTag, BlogTagBO.class)));
    }

    @RequiresPermissions("blog:tag:edit")
    @OperationLog(title = "Blog Tag", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogTagVOParams blogTag) {
        return toAjax(blogTagService.updateBlogTag(BeanMapper.convert(blogTag, BlogTagBO.class)));
    }

    @RequiresPermissions("blog:tag:remove")
    @OperationLog(title = "Blog Tag", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blogTagService.deleteBlogTagByIds(ids));
    }
}
