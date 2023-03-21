package com.dimple.blog.web.controller;

import com.dimple.blog.service.service.BlogArticleService;
import com.dimple.blog.service.service.bo.BlogArticleBO;
import com.dimple.blog.web.controller.vo.BlogArticleVO;
import com.dimple.blog.web.controller.vo.params.BlogArticleVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @RequiresPermissions("blog:article:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogArticleVOParams blogArticle) {
        startPage();
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        List<BlogArticleBO> list = blogArticleService.selectBlogArticleList(blogArticleBO);
        return getDataTable(BeanMapper.convertList(list, BlogArticleVO.class));
    }

    @RequiresPermissions("blog:article:export")
    @OperationLog(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogArticleVOParams blogArticle) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        List<BlogArticleBO> list = blogArticleService.selectBlogArticleList(blogArticleBO);
        ExcelUtil<BlogArticleBO> util = new ExcelUtil<>(BlogArticleBO.class);
        util.exportExcel(response, list, "文章数据");
    }

    @RequiresPermissions("blog:article:query")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        BlogArticleBO blogArticleBO = blogArticleService.selectBlogArticleById(id);
        return success(BeanMapper.convert(blogArticleBO, BlogArticleVO.class));
    }

    @RequiresPermissions("blog:article:add")
    @OperationLog(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogArticleVOParams blogArticle) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        return success(blogArticleService.insertBlogArticle(blogArticleBO));
    }

    @RequiresPermissions("blog:article:edit")
    @OperationLog(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping("{id}")
    public AjaxResult edit(@PathVariable Long id, @RequestBody BlogArticleVOParams blogArticle) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticle, BlogArticleBO.class);
        blogArticleBO.setId(id);
        return toAjax(blogArticleService.updateBlogArticle(blogArticleBO));
    }

    @RequiresPermissions("blog:article:edit")
    @OperationLog(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}/status/{articleStatus}}")
    public AjaxResult edit(@PathVariable Long id, @PathVariable Integer articleStatus) {
        return toAjax(blogArticleService.updateBlogArticleStatus(id, articleStatus));
    }

    @RequiresPermissions("blog:article:remove")
    @OperationLog(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(blogArticleService.deleteBlogArticleByIds(ids));
    }
}
