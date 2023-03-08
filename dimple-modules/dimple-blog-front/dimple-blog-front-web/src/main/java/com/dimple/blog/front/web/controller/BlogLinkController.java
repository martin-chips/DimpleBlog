package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogLinkService;
import com.dimple.blog.front.service.service.bo.BlogLinkBO;
import com.dimple.blog.front.web.controller.vo.BlogLinkVO;
import com.dimple.blog.front.web.controller.vo.params.BlogLinkVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BlogLinkController
 *
 * @author Dimple
 * @date 3/8/2023 7:39 PM
 */
@RestController
@RequestMapping("/link")
public class BlogLinkController extends BaseController {

    @Autowired
    private BlogLinkService blogLinkService;

    @GetMapping("/list")
    public TableDataInfo list(BlogLinkVOParams params) {
        startPage();
        BlogLinkBO blogLinkBO = BeanMapper.convert(params, BlogLinkBO.class);
        List<BlogLinkBO> list = blogLinkService.listBlogLink(blogLinkBO);
        return getDataTable(BeanMapper.convertList(list, BlogLinkVO.class));
    }


    @PostMapping
    public AjaxResult add(@RequestBody @Validated BlogLinkVOParams params) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(params, BlogLinkBO.class);
        return toAjax(blogLinkService.insertLink(blogLinkBO));
    }

    @PutMapping("{id}/visitCount")
    public AjaxResult count(@PathVariable Long id) {
        return toAjax(blogLinkService.addLinkVisitCount(id));
    }
}
