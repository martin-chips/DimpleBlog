package com.dimple.blog.web.controller;

import com.dimple.blog.service.service.BlogCategoryService;
import com.dimple.blog.service.service.bo.BlogCategoryBO;
import com.dimple.blog.web.controller.vo.BlogCategoryVO;
import com.dimple.blog.web.controller.vo.params.BlogCategoryVOParams;
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
 * blog categoryController
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/category")
public class BlogCategoryController extends BaseController {
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequiresPermissions("blog:category:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogCategoryVOParams blogCategory) {
        startPage();
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        List<BlogCategoryBO> list = blogCategoryService.selectBlogCategoryList(blogCategoryBO);
        List<BlogCategoryVO> blogCategoryVOS = BeanMapper.convertList(list, BlogCategoryVO.class);
        return getDataTable(blogCategoryVOS);
    }

    @RequiresPermissions("blog:category:export")
    @OperationLog(title = "blog category", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogCategoryVOParams blogCategory) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        List<BlogCategoryBO> list = blogCategoryService.selectBlogCategoryList(blogCategoryBO);
        ExcelUtil<BlogCategoryBO> util = new ExcelUtil<>(BlogCategoryBO.class);
        util.exportExcel(response, list, "blog category数据");
    }

    @RequiresPermissions("blog:category:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogCategoryService.selectBlogCategoryById(id));
    }

    @RequiresPermissions("blog:category:add")
    @OperationLog(title = "blog category", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogCategoryVOParams blogCategory) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        return toAjax(blogCategoryService.insertBlogCategory(blogCategoryBO));
    }

    @RequiresPermissions("blog:category:edit")
    @OperationLog(title = "blog category", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogCategoryVOParams blogCategory) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        return toAjax(blogCategoryService.updateBlogCategory(blogCategoryBO));
    }

    @RequiresPermissions("blog:category:remove")
    @OperationLog(title = "blog category", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blogCategoryService.deleteBlogCategoryByIds(ids));
    }
}
