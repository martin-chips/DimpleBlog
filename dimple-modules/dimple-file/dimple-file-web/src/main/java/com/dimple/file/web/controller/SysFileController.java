package com.dimple.file.web.controller;

import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.response.ResponseEntityUtils;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.file.service.service.SysFileService;
import com.dimple.file.web.controller.vo.SysFileVO;
import com.dimple.file.web.controller.vo.params.SysFileVOParams;
import com.dimple.system.api.model.SysFileBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件请求处理
 *
 * @author Dimple
 */
@RestController
@RequestMapping("file")
@Slf4j
public class SysFileController extends BaseController {

    @Autowired
    private SysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public ResponseEntity<SysFileBO> upload(MultipartFile file, @RequestParam(required = false, defaultValue = "qiNiu") String type) {
        try {
            // 上传并返回访问地址
            SysFileBO sysFile = sysFileService.upload(file, type);
            return ResponseEntityUtils.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return ResponseEntityUtils.fail(e.getMessage());
        }
    }

    @RequiresPermissions("file:file:list")
    @GetMapping("/list")
    public TableDataInfo list(SysFileVOParams params) {
        startPage();
        List<SysFileBO> list = sysFileService.selectFileList(BeanMapper.convert(params, SysFileBO.class));
        return getDataTable(BeanMapper.convertList(list, SysFileVO.class));
    }


    @RequiresPermissions("file:file:list")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        return toAjax(sysFileService.deleteFile(id));
    }

    @RequiresPermissions("file:file:list")
    @PutMapping("/{id}/name")
    public AjaxResult list(@PathVariable Long id, @RequestBody SysFileVOParams params) {
        return toAjax(sysFileService.updateFileName(id, params.getName()));
    }
}