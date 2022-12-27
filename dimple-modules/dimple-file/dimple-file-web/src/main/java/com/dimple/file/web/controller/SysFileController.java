package com.dimple.file.web.controller;

import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.file.FileUtils;
import com.dimple.common.core.utils.response.ResponseEntityUtils;
import com.dimple.file.service.service.SysFileService;
import com.dimple.system.api.model.SysFileBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 *
 * @author Dimple
 */
@RestController
@Slf4j
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public ResponseEntity<SysFileBO> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFileBO sysFile = new SysFileBO();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return ResponseEntityUtils.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return ResponseEntityUtils.fail(e.getMessage());
        }
    }
}