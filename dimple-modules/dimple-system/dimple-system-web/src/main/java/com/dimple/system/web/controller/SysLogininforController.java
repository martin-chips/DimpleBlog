package com.dimple.system.web.controller;

import com.dimple.common.core.constant.CacheConstants;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.redis.service.RedisService;
import com.dimple.common.security.annotation.InnerAuth;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.api.model.SysLogininforBO;
import com.dimple.system.service.service.SysLogininforService;
import com.dimple.system.web.controller.vo.SysLogininforVO;
import com.dimple.system.web.controller.vo.params.SysLogininforVOParams;
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
 * 系统访问记录
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private SysLogininforService logininforService;

    @Autowired
    private RedisService redisService;

    @RequiresPermissions("system:logininfor:list")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininforVOParams logininfor) {
        startPage();
        List<SysLogininforBO> list = logininforService.selectLogininforList(BeanMapper.convert(logininfor, SysLogininforBO.class));
        return getDataTable(BeanMapper.convertList(list, SysLogininforVO.class));
    }

    @OperationLog(title = "登录日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:logininfor:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininforVOParams logininfor) {
        List<SysLogininforBO> list = logininforService.selectLogininforList(BeanMapper.convert(logininfor, SysLogininforBO.class));
        ExcelUtil<SysLogininforVO> util = new ExcelUtil<>(SysLogininforVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysLogininforVO.class), "登录日志");
    }

    @RequiresPermissions("system:logininfor:remove")
    @OperationLog(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @RequiresPermissions("system:logininfor:remove")
    @OperationLog(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("system:logininfor:unlock")
    @OperationLog(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public AjaxResult unlock(@PathVariable("userName") String userName) {
        redisService.deleteObject(CacheConstants.PWD_ERR_CNT_KEY + userName);
        return success();
    }

    @InnerAuth
    @PostMapping
    public AjaxResult add(@RequestBody SysLogininforVOParams logininfor) {
        return toAjax(logininforService.insertLogininfor(BeanMapper.convert(logininfor, SysLogininforBO.class)));
    }
}
