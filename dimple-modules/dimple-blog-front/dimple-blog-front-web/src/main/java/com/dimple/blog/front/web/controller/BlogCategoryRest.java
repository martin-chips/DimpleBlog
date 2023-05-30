package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.BlogCategoryBO;
import com.dimple.blog.front.service.service.BlogRestCategoryService;
import com.dimple.blog.front.web.controller.vo.BlogCategoryVO;
import com.dimple.blog.front.web.controller.vo.params.BlogCategoryVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * blog categoryController
 *
 * @author Dimple
 * @date 2023-02-13
 */
@RestController
@RequestMapping("/category")
public class BlogCategoryRest extends BaseController {
    @Autowired
    private BlogRestCategoryService blogRestCategoryService;

    @GetMapping("/list")
    public TableDataInfo list(BlogCategoryVOParams blogCategory) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategory, BlogCategoryBO.class);
        List<BlogCategoryBO> list = blogRestCategoryService.selectBlogCategoryList(blogCategoryBO);
        List<BlogCategoryVO> blogCategoryVOS = BeanMapper.convertList(list, BlogCategoryVO.class);
        return getDataTable(blogCategoryVOS);
    }
}
