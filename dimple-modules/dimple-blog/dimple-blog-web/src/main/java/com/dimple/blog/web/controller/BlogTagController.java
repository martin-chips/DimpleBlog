package com.dimple.blog.web.controller;

import com.dimple.blog.api.bo.BlogArticleTagBO;
import com.dimple.blog.api.bo.BlogTagBO;
import com.dimple.blog.api.bo.BlogTagDTO;
import com.dimple.blog.service.service.BlogArticleTagService;
import com.dimple.blog.service.service.BlogTagService;
import com.dimple.blog.web.controller.vo.BlogTagVO;
import com.dimple.blog.web.controller.vo.params.BlogTagVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.InnerAuth;
import com.dimple.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @RequiresPermissions("blog:tag:list")
    @InnerAuth
    @GetMapping("/list")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        startPage();
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @RequiresPermissions("blog:tag:export")
    @OperationLog(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogTagVOParams blogTag) {
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        ExcelUtil<BlogTagBO> util = new ExcelUtil<>(BlogTagBO.class);
        util.exportExcel(response, list, "标签 数据");
    }

    @RequiresPermissions("blog:tag:query")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogTagService.selectBlogTagById(id));
    }

    @RequiresPermissions("blog:tag:add")
    @OperationLog(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogTagVOParams blogTag) {
        return toAjax(blogTagService.insertBlogTag(BeanMapper.convert(blogTag, BlogTagBO.class)));
    }

    @RequiresPermissions("blog:tag:edit")
    @OperationLog(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping("{id}")
    public AjaxResult edit(@PathVariable Long id, @RequestBody BlogTagVOParams blogTag) {
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        blogTagBO.setId(id);
        return toAjax(blogTagService.updateBlogTag(blogTagBO));
    }

    @RequiresPermissions("blog:tag:remove")
    @OperationLog(title = "标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blogTagService.deleteBlogTagByIds(ids));
    }


    @InnerAuth
    @PostMapping("/inner/list")
    public TableDataInfo innerGetTagList(@RequestBody BlogTagDTO blogTag) {
        startInnerPage(blogTag);
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @InnerAuth
    @GetMapping("inner/{ids}")
    public AjaxResult getTags(@PathVariable List<Long> ids) {
        List<BlogTagBO> blogTagBOS = blogTagService.selectBlogTagByIds(ids);
        return success(blogTagBOS);
    }

    @InnerAuth
    @GetMapping("/inner/article/{articleId}")
    AjaxResult selectBlogArticleTagByArticleId(@PathVariable Long articleId) {
        List<BlogArticleTagBO> blogArticleTagBOS = blogArticleTagService.selectBlogArticleTagByArticleId(articleId);
        return success(blogArticleTagBOS);
    }
}
