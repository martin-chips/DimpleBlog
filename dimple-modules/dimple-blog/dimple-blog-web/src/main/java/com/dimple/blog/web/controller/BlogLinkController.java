package com.dimple.blog.web.controller;

import com.dimple.blog.api.bo.BlogLinkBO;
import com.dimple.blog.api.model.BlogLinkDTO;
import com.dimple.blog.service.service.BlogLinkService;
import com.dimple.blog.web.controller.vo.BlogLinkVO;
import com.dimple.blog.web.controller.vo.params.BlogLinkVOParams;
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
@RequestMapping("/link")
public class BlogLinkController extends BaseController {
    @Autowired
    private BlogLinkService blogLinkService;

    @RequiresPermissions("blog:link:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogLinkVOParams blogLink) {
        startPage();
        BlogLinkBO blogLinkBO = BeanMapper.convert(blogLink, BlogLinkBO.class);
        List<BlogLinkBO> list = blogLinkService.selectBlogLinkList(blogLinkBO);
        return getDataTable(BeanMapper.convertList(list, BlogLinkVO.class));
    }

    @RequiresPermissions("blog:link:export")
    @OperationLog(title = "友链", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogLinkVOParams blogLink) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(blogLink, BlogLinkBO.class);
        List<BlogLinkBO> list = blogLinkService.selectBlogLinkList(blogLinkBO);
        ExcelUtil<BlogLinkBO> util = new ExcelUtil<>(BlogLinkBO.class);
        util.exportExcel(response, list, "友链数据");
    }

    @RequiresPermissions("blog:link:query")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        BlogLinkBO blogLinkBO = blogLinkService.selectBlogLinkById(id);
        return success(BeanMapper.convert(blogLinkBO, BlogLinkVO.class));
    }

    @RequiresPermissions("blog:link:add")
    @OperationLog(title = "友链", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogLinkVOParams blogLink) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(blogLink, BlogLinkBO.class);
        return toAjax(blogLinkService.insertBlogLink(blogLinkBO));
    }

    @RequiresPermissions("blog:link:edit")
    @OperationLog(title = "友链", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}")
    public AjaxResult edit(@PathVariable Long id, @RequestBody BlogLinkVOParams blogLink) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(blogLink, BlogLinkBO.class);
        blogLinkBO.setId(id);
        return toAjax(blogLinkService.updateBlogLink(blogLinkBO));
    }

    @RequiresPermissions("blog:link:remove")
    @OperationLog(title = "友链", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blogLinkService.deleteBlogLinkByIds(ids));
    }

    @InnerAuth
    @PostMapping("/inner")
    AjaxResult insertBlogLink(@RequestBody BlogLinkBO blogLink) {
        return success(blogLinkService.insertBlogLink(blogLink));
    }

    @InnerAuth
    @PostMapping("/inner/list")
    TableDataInfo selectBlogLinkList(@RequestBody BlogLinkDTO blogLink) {
        startInnerPage(blogLink);
        List<BlogLinkBO> blogLinkBOS = blogLinkService.selectBlogLinkList(BeanMapper.convert(blogLink, BlogLinkBO.class));
        return getDataTable(blogLinkBOS);
    }

    @InnerAuth
    @PutMapping("/inner/visitCount/{id}")
    AjaxResult addLinkVisitCount(@PathVariable Long id) {
        return success(blogLinkService.addLinkVisitCount(id));
    }
}
