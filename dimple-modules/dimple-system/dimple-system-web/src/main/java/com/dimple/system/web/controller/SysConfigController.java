package com.dimple.system.web.controller;

import com.dimple.common.core.constant.UserConstants;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.service.service.SysConfigService;
import com.dimple.system.service.service.bo.SysConfigBO;
import com.dimple.system.web.controller.vo.SysConfigVO;
import com.dimple.system.web.controller.vo.params.SysConfigVOParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/config")
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @RequiresPermissions("system:config:list")
    @GetMapping("/list")
    public TableDataInfo list(SysConfigVOParams config) {
        startPage();
        List<SysConfigBO> list = configService.selectConfigList(BeanMapper.convert(config, SysConfigBO.class));
        return getDataTable(BeanMapper.convertList(list, SysConfigVO.class));
    }

    @OperationLog(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfigVOParams config) {
        List<SysConfigBO> list = configService.selectConfigList(BeanMapper.convert(config, SysConfigBO.class));
        ExcelUtil<SysConfigVO> util = new ExcelUtil<>(SysConfigVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysConfigVO.class), "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        SysConfigBO sysConfigBO = configService.selectConfigById(id);
        return success(BeanMapper.convert(sysConfigBO, SysConfigVO.class));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping("/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey) {
        String configValue = configService.selectConfigByKey(configKey);
        return success(configValue);
    }

    /**
     * 新增参数配置
     */
    @RequiresPermissions("system:config:add")
    @OperationLog(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfigVOParams config) {
        SysConfigBO sysConfigBO = BeanMapper.convert(config, SysConfigBO.class);
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(sysConfigBO))) {
            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.insertConfig(sysConfigBO));
    }

    /**
     * 修改参数配置
     */
    @RequiresPermissions("system:config:edit")
    @OperationLog(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfigVOParams config) {
        SysConfigBO sysConfigBO = BeanMapper.convert(config, SysConfigBO.class);
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(sysConfigBO))) {
            return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.updateConfig(sysConfigBO));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:remove")
    @OperationLog(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        configService.deleteConfigByIds(ids);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    @RequiresPermissions("system:config:remove")
    @OperationLog(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return success();
    }
}
