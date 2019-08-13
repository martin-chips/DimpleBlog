package com.dimple.project.common.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.common.domain.FileItemInfo;
import com.dimple.project.common.service.FileService;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @className: QiniuyunController
 * @description: 七牛云图片上传处理
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/qiniu/image")
public class QiNiuController {

    @Autowired
    FileService fileService;

    @GetMapping()
    @RequiresPermissions("qiniu:image:view")
    public String qiniuyun() {
        return "";
    }

    @PostMapping("/upload")
    @Log(title = "七牛云图片", businessType = BusinessType.UPLOAD)
    @RequiresPermissions("qiniu:image:upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file) {
        String s = fileService.insertQiNiuYunImageFile(file);
        return AjaxResult.success().put("data", s);
    }

    @DeleteMapping("/remove")
    @Log(title = "七牛云图片", businessType = BusinessType.DELETE)
    @ResponseBody
    public boolean remove(String fileName) throws QiniuException {
        log.info("删除图片{}", fileName);
        return fileService.deleteQiNiuYunImageFile(fileName) > 0 ? true : false;
    }

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        FileItemInfo fileItemInfo = new FileItemInfo();
        fileItemInfo.setServerType(FileItemInfo.ServerType.QI_NIU_YUN.getServerType());
        List<FileItemInfo> list = fileService.selectFileItemImageList(fileItemInfo);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("list", list);
        return ajaxResult;
    }

}
