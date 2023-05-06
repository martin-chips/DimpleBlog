package com.dimple.blog.front.web.controller;

import com.dimple.blog.api.bo.BlogLinkBO;
import com.dimple.blog.front.service.service.BlogRestLinkService;
import com.dimple.blog.front.web.controller.vo.BlogLinkVO;
import com.dimple.blog.front.web.controller.vo.params.BlogLinkVOParams;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.VisitLogTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BlogLinkController
 *
 * @author Dimple
 * @date 3/8/2023 7:39 PM
 */
@RestController
@RequestMapping("/link")
public class BlogLinkRest extends BaseController {

    @Autowired
    private BlogRestLinkService blogLinkService;

    @GetMapping("/list")
    @VisitLog(title = VisitLogTitle.LIST_LINK)
    public TableDataInfo list(BlogLinkVOParams params) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(params, BlogLinkBO.class);
        List<BlogLinkBO> list = blogLinkService.listBlogLink(blogLinkBO);
        return getDataTable(BeanMapper.convertList(list, BlogLinkVO.class));
    }


    @PostMapping
    @VisitLog(title = VisitLogTitle.ADD_LINK)
    public AjaxResult add(@RequestBody @Validated BlogLinkVOParams params) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(params, BlogLinkBO.class);
        return toAjax(blogLinkService.insertLink(blogLinkBO));
    }

    @PutMapping("{id}/visitCount")
    @VisitLog(title = VisitLogTitle.VISIT_LINK, pageId = "#id")
    public AjaxResult count(@PathVariable Long id) {
        return toAjax(blogLinkService.addLinkVisitCount(id));
    }
}
