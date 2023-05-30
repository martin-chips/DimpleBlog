package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.BlogTagBO;
import com.dimple.blog.front.service.service.BlogRestTagService;
import com.dimple.blog.front.web.controller.vo.BlogTagVO;
import com.dimple.blog.front.web.controller.vo.params.BlogTagVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BlogTagRest extends BaseController {
    @Autowired
    private BlogRestTagService blogRestTagService;

    @GetMapping("/list")
    public TableDataInfo list(BlogTagVOParams blogTag) {
        BlogTagBO blogTagBO = BeanMapper.convert(blogTag, BlogTagBO.class);
        List<BlogTagBO> list = blogRestTagService.selectBlogTagList(blogTagBO);
        return getDataTable(BeanMapper.convertList(list, BlogTagVO.class));
    }
}
