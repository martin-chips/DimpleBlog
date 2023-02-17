package com.dimple.system.web.controller;

import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.api.model.SysDictDataBO;
import com.dimple.system.service.service.SysDictDataService;
import com.dimple.system.service.service.SysDictTypeService;
import com.dimple.system.web.controller.vo.SysDictDataVO;
import com.dimple.system.web.controller.vo.params.SysDictDataVOParams;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictDataVOParams dictData) {
        startPage();
        List<SysDictDataBO> list = dictDataService.selectDictDataList(BeanMapper.convert(dictData, SysDictDataBO.class));
        return getDataTable(BeanMapper.convertList(list, SysDictDataVO.class));
    }

    @OperationLog(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictDataVOParams dictData) {
        List<SysDictDataBO> list = dictDataService.selectDictDataList(BeanMapper.convert(dictData, SysDictDataBO.class));
        ExcelUtil<SysDictDataVO> util = new ExcelUtil<>(SysDictDataVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysDictDataVO.class), "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @RequiresPermissions("system:dict:query")
    @GetMapping("/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping("/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictDataBO> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<>();
        }
        return success(BeanMapper.convertList(data, SysDictDataVO.class));
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @OperationLog(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictDataVOParams dict) {
        SysDictDataBO sysDictDataBO = BeanMapper.convert(dict, SysDictDataBO.class);
        return toAjax(dictDataService.insertDictData(sysDictDataBO));
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @OperationLog(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictDataVOParams dict) {
        SysDictDataBO sysDictDataBO = BeanMapper.convert(dict, SysDictDataBO.class);
        return toAjax(dictDataService.updateDictData(sysDictDataBO));
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("system:dict:remove")
    @OperationLog(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
