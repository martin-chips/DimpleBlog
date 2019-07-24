package com.dimple.project.file.image;

import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.common.domain.FileItemInfo;
import com.dimple.project.common.service.FileService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @className: ImageController
 * @description: 图片文件处理Controller
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
@Controller("/file/image")
public class ImageController {

    @Autowired
    FileService fileService;

    @GetMapping("/add")
    public String add() {
        return "file/image/upload";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestParam("file") MultipartFile file) {
        String path = fileService.insertQiNiuYunFile(file);
        return AjaxResult.success().put("path", path);
    }

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(FileItemInfo fileItemInfo) {
        List<FileItemInfo> fileItemInfoList = fileService.selectFileItemList(fileItemInfo);
        return AjaxResult.success().put("list", fileItemInfoList);
    }

    @PutMapping("/syncQiNiuYun")
    public AjaxResult syncQiNiuYun() throws QiniuException {
        int i = fileService.syncQiNiuYunToLocalDB();
        return AjaxResult.success().put("count", i);
    }

}
