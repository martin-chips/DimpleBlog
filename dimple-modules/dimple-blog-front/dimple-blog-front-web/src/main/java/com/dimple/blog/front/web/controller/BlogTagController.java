package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogTagService;
import com.dimple.blog.front.service.service.bo.BlogTagBO;
import com.dimple.blog.front.web.controller.vo.BlogTagVO;
import com.dimple.blog.front.web.controller.vo.params.BlogTagVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/tag")
public class BlogTagController extends BaseController {
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/list")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        startPage();
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blogTagService.selectBlogTagById(id));
    }

}
