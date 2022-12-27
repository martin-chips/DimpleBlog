package com.dimple.system.web.controller;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.Log;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.InnerAuth;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.api.model.SysOperLogBO;
import com.dimple.system.service.service.SysOperLogService;
import com.dimple.system.web.controller.vo.SysOperLogVO;
import com.dimple.system.web.controller.vo.params.SysOperLogVOParams;
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
 * 操作日志记录
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/operlog")
public class SysOperlogController extends BaseController {
    @Autowired
    private SysOperLogService operLogService;

    @RequiresPermissions("system:operlog:list")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLogVOParams operLog) {
        startPage();
        SysOperLogBO sysOperLogBO = BeanMapper.convert(operLog, SysOperLogBO.class);
        List<SysOperLogBO> list = operLogService.selectOperLogList(sysOperLogBO);
        return getDataTable(BeanMapper.convertList(list, SysOperLogVO.class));
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:operlog:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperLogVOParams operLog) {
        SysOperLogBO sysOperLogBO = BeanMapper.convert(operLog, SysOperLogBO.class);
        List<SysOperLogBO> list = operLogService.selectOperLogList(sysOperLogBO);
        ExcelUtil<SysOperLogVO> util = new ExcelUtil<>(SysOperLogVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysOperLogVO.class), "操作日志");
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:operlog:remove")
    @DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable Long[] operIds) {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @RequiresPermissions("system:operlog:remove")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        operLogService.cleanOperLog();
        return success();
    }

    @InnerAuth
    @PostMapping
    public AjaxResult add(@RequestBody SysOperLogVOParams operLog) {
        SysOperLogBO sysOperLogBO = BeanMapper.convert(operLog, SysOperLogBO.class);
        return toAjax(operLogService.insertOperlog(sysOperLogBO));
    }
}
