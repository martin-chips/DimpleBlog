package com.dimple.log.web.controller;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.InnerAuth;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.log.service.service.BlogVisitLogService;
import com.dimple.log.web.controller.vo.BlogVisitLogVO;
import com.dimple.log.web.controller.vo.params.BlogVisitLogVOParams;
import com.dimple.system.api.model.BlogVisitLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * VisitLogController
 *
 * @author Dimple
 * @date 3/6/2023 8:27 PM
 */
@RestController
@RequestMapping("/visitLog")
public class BlogVisitLogController extends BaseController {
    @Autowired
    private BlogVisitLogService blogVisitLogService;

    @RequiresPermissions("log:visitLog:list")
    @GetMapping("/list")
    public TableDataInfo list(BlogVisitLogVOParams params) {
        startPage();
        BlogVisitLogBO blogVisitLogBO = BeanMapper.convert(params, BlogVisitLogBO.class);
        List<BlogVisitLogBO> list = blogVisitLogService.selectVisitLogList(blogVisitLogBO);
        return getDataTable(BeanMapper.convertList(list, BlogVisitLogVO.class));
    }

    @OperationLog(title = "访问日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("log:visitLog:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogVisitLogVOParams params) {
        BlogVisitLogBO blogVisitLogBO = BeanMapper.convert(params, BlogVisitLogBO.class);
        List<BlogVisitLogBO> list = blogVisitLogService.selectVisitLogList(blogVisitLogBO);
        ExcelUtil<BlogVisitLogVO> util = new ExcelUtil<>(BlogVisitLogVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, BlogVisitLogVO.class), "访问日志");
    }

    @OperationLog(title = "访问日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("log:visitLog:remove")
    @DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(blogVisitLogService.deleteVisitLogByIds(ids));
    }

    @RequiresPermissions("log:visitLog:remove")
    @OperationLog(title = "访问日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        blogVisitLogService.cleanVisitLog();
        return success();
    }

    @InnerAuth
    @PostMapping
    public AjaxResult add(@RequestBody BlogVisitLogVOParams visitLog) {
        BlogVisitLogBO blogVisitLogBO = BeanMapper.convert(visitLog, BlogVisitLogBO.class);
        return toAjax(blogVisitLogService.insertVisitLog(blogVisitLogBO));
    }
}
