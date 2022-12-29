package com.dimple.system.web.controller;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.Log;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.service.service.SysNoticeService;
import com.dimple.system.service.service.bo.SysNoticeBO;
import com.dimple.system.web.controller.vo.SysNoticeVO;
import com.dimple.system.web.controller.vo.params.SysNoticeVOParams;
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

import java.util.List;

/**
 * 公告 信息操作处理
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController extends BaseController {
    @Autowired
    private SysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @RequiresPermissions("system:notice:list")
    @GetMapping("/list")
    public TableDataInfo list(SysNoticeVOParams notice) {
        startPage();
        SysNoticeBO sysNoticeBO = BeanMapper.convert(notice, SysNoticeBO.class);
        List<SysNoticeBO> list = noticeService.selectNoticeList(sysNoticeBO);
        return getDataTable(BeanMapper.convertList(list, SysNoticeVO.class));
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @RequiresPermissions("system:notice:query")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        SysNoticeBO sysNoticeBO = noticeService.selectNoticeById(noticeId);
        return success(BeanMapper.convert(sysNoticeBO, SysNoticeVO.class));
    }

    /**
     * 新增通知公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNoticeVOParams notice) {
        SysNoticeBO sysNoticeBO = BeanMapper.convert(notice, SysNoticeBO.class);
        return toAjax(noticeService.insertNotice(sysNoticeBO));
    }

    /**
     * 修改通知公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNoticeVOParams notice) {
        SysNoticeBO sysNoticeBO = BeanMapper.convert(notice, SysNoticeBO.class);
        return toAjax(noticeService.updateNotice(sysNoticeBO));
    }

    /**
     * 删除通知公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
