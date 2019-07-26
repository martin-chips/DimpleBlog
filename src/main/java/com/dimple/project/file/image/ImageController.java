package com.dimple.project.file.image;

import com.dimple.common.utils.file.FileUploadUtils;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.common.domain.FileItemInfo;
import com.dimple.project.common.service.FileService;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @className: ImageController
 * @description: 图片文件处理Controller
 * @auther: Dimple
 * @date: 07/24/19
 * @version: 1.0
 */
@Controller
@RequestMapping("/file/image")
public class ImageController {

    @Autowired
    FileService fileService;

    @GetMapping("/add")
    public String add(Integer serverType, Model model) {
        model.addAttribute("serverType", serverType == null ? FileItemInfo.ServerType.LOCAL.getServerType() : FileItemInfo.ServerType.QI_NIU_YUN.getServerType());
        return "file/image/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestParam("file") MultipartFile file, Integer serverType) throws IOException {
        Objects.requireNonNull(serverType, "上传服务器未选定，请重试！");
        //检查文件大小
        FileUploadUtils.assertAllowed(file);
        String path = null;
        if (FileItemInfo.ServerType.QI_NIU_YUN.getServerType() == serverType) {
            path = fileService.insertQiNiuYunFile(file);
        } else if (FileItemInfo.ServerType.LOCAL.getServerType() == serverType) {
            path = fileService.insertLocalFile(file);
        }
        return AjaxResult.success().put("path", path);
    }

    @GetMapping("/list/{serverType}")
    @ResponseBody
    public AjaxResult list(FileItemInfo fileItemInfo, @PathVariable Integer serverType) {
        fileItemInfo.setServerType(serverType);
        List<FileItemInfo> fileItemInfoList = fileService.selectFileItemList(fileItemInfo);
        return AjaxResult.success().put("list", fileItemInfoList);
    }

    @PutMapping("/syncQiNiuYun")
    @ResponseBody
    public AjaxResult syncQiNiuYun() throws QiniuException {
        int i = fileService.syncQiNiuYunToLocalDB();
        return AjaxResult.success().put("count", i);
    }

    @PutMapping("/syncLocal")
    @ResponseBody
    public AjaxResult syncLocal() {
        int i = fileService.syncLocalImageToDB();
        return AjaxResult.success();
    }


    @GetMapping()
    @ResponseBody
    public AjaxResult deleteQiNiuYun(String name) throws QiniuException {
        int i = fileService.deleteQiNiuYunFile(name);
        return AjaxResult.success().put("count", i);
    }
}
