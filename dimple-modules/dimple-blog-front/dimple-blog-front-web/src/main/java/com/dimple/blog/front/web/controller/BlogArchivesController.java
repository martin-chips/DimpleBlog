package com.dimple.blog.front.web.controller;

import com.dimple.blog.front.service.service.BlogArchivesService;
import com.dimple.blog.front.service.service.bo.BlogArchivesBO;
import com.dimple.blog.front.web.controller.vo.BlogArchivesVO;
import com.dimple.blog.front.web.controller.vo.params.BlogArchivesVOParams;
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
 * BlogArchivesController
 *
 * @author Dimple
 * @date 2/28/2023 11:06 AM
 */
@RestController
@RequestMapping("/archives")
public class BlogArchivesController extends BaseController {

    @Autowired
    private BlogArchivesService archivesService;

    @GetMapping
    @VisitLog(title = VisitLogTitle.LIST_ARCHIVES)
    public TableDataInfo getArchives(BlogArchivesVOParams params) {
        startPage();
        List<BlogArchivesBO> archives = archivesService.getArchives(params.getCountType());
        return getDataTable(BeanMapper.convertList(archives, BlogArchivesVO.class));
    }
}
