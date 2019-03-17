package com.dimple.project.common;

import com.dimple.framework.aspectj.lang.annotation.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className: QiniuyunController
 * @description: 七牛云图片上传处理
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Controller
@RequestMapping("/qiniuyun/image")
public class QiniuyunController {


    @GetMapping()
    @RequiresPermissions("qiniuyun:image:view")
    public String qiniuyun() {
        return "";
    }

    @PostMapping("/upload")
    @Log(title = "图片管理")
    @RequiresPermissions("qiniuyun:image:upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        return "";
    }
}
