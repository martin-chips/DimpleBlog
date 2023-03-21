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
import com.dimple.log.service.service.SysLogininforService;
import com.dimple.log.web.controller.vo.SysLogininforVO;
import com.dimple.log.web.controller.vo.params.SysLogininforVOParams;
import com.dimple.system.api.model.SysLogininforBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private SysLogininforService logininforService;

    @RequiresPermissions("log:logininfor:list")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininforVOParams logininfor) {
        startPage();
        List<SysLogininforBO> list = logininforService.selectLogininforList(BeanMapper.convert(logininfor, SysLogininforBO.class));
        return getDataTable(BeanMapper.convertList(list, SysLogininforVO.class));
    }

    @OperationLog(title = "登录日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("log:logininfor:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininforVOParams logininfor) {
        List<SysLogininforBO> list = logininforService.selectLogininforList(BeanMapper.convert(logininfor, SysLogininforBO.class));
        ExcelUtil<SysLogininforVO> util = new ExcelUtil<>(SysLogininforVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysLogininforVO.class), "登录日志");
    }

    @RequiresPermissions("log:logininfor:remove")
    @OperationLog(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @RequiresPermissions("log:logininfor:remove")
    @OperationLog(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return success();
    }

    @InnerAuth
    @PostMapping
    public AjaxResult add(@RequestBody SysLogininforVOParams logininfor) {
        return toAjax(logininforService.insertLogininfor(BeanMapper.convert(logininfor, SysLogininforBO.class)));
    }
}
