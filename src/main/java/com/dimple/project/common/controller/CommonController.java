package com.dimple.project.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dimple.common.utils.file.FileUploadUtils;
import com.dimple.framework.config.DimpleBlogConfig;
import com.dimple.framework.config.ServerConfig;
import com.dimple.framework.web.domain.AjaxResult;

/**
 * @className: CommonController
 * @description: 通用请求处理
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
public class CommonController {
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = DimpleBlogConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
