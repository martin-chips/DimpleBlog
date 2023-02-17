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
import com.dimple.system.service.service.SysDictTypeService;
import com.dimple.system.service.service.bo.SysDictTypeBO;
import com.dimple.system.web.controller.vo.SysDictTypeVO;
import com.dimple.system.web.controller.vo.params.SysDictTypeVOParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private SysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictTypeVOParams dictType) {
        startPage();
        List<SysDictTypeBO> list = dictTypeService.selectDictTypeList(BeanMapper.convert(dictType, SysDictTypeBO.class));
        return getDataTable(BeanMapper.convertList(list, SysDictTypeVO.class));
    }

    @OperationLog(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictTypeVOParams dictType) {
        List<SysDictTypeBO> list = dictTypeService.selectDictTypeList(BeanMapper.convert(dictType, SysDictTypeBO.class));
        ExcelUtil<SysDictTypeVO> util = new ExcelUtil<>(SysDictTypeVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysDictTypeVO.class), "字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @RequiresPermissions("system:dict:query")
    @GetMapping("/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId) {
        SysDictTypeBO sysDictTypeBO = dictTypeService.selectDictTypeById(dictId);
        return success(BeanMapper.convert(sysDictTypeBO, SysDictTypeVO.class));
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @OperationLog(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictTypeVOParams dict) {
        SysDictTypeBO sysDictTypeBO = BeanMapper.convert(dict, SysDictTypeBO.class);
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(sysDictTypeBO))) {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.insertDictType(sysDictTypeBO));
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @OperationLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictTypeVOParams dict) {
        SysDictTypeBO sysDictTypeBO = BeanMapper.convert(dict, SysDictTypeBO.class);
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(sysDictTypeBO))) {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.updateDictType(sysDictTypeBO));
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("system:dict:remove")
    @OperationLog(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @RequiresPermissions("system:dict:remove")
    @OperationLog(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache() {
        dictTypeService.resetDictCache();
        return success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionSelect() {
        List<SysDictTypeBO> dictTypes = dictTypeService.selectDictTypeAll();
        return success(BeanMapper.convertList(dictTypes, SysDictTypeVO.class));
    }
}
