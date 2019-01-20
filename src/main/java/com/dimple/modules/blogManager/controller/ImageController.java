package com.dimple.modules.blogManager.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.utils.FileOperateUtil;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @ClassName: ImageController
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/15 22:36
 * @Version: 1.0
 */
@Api("文件上传")
@Controller
@Slf4j
public class ImageController {

    @Autowired
    FileOperateUtil fileOperateUtil;

    @Autowired
    FileUtil fileUtil;

    @ApiIgnore
    @ApiOperation("跳转到上传图片界面")
    @GetMapping("/page/imageUploadPage")
    public String imageUploadPage() {
        return "blog/imageUpload";
    }

    @ApiOperation("SummerNote编辑器中上传图片接口")
    @RequestMapping(value = "/api/summernote/image", method = RequestMethod.POST)
    @Log(title = "文件上传", operateType = OperateType.FILE_UPLOAD)
    @ResponseBody
    public Result uploadImageSummernote(@RequestParam("file") MultipartFile file) throws Exception {
        return ResultUtil.success((fileUtil.uploadImgToQiNiu(file)));
    }

    @ApiOperation("上传图片文件")
    @Log(title = "文件上传", operateType = OperateType.FILE_UPLOAD)
    @RequestMapping(value = "/api/image", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return ResultUtil.success((fileUtil.uploadImgToQiNiu(file)));
    }


    @ApiOperation("SummerNote编辑器中删除图片同步删除服务器图片")
    @DeleteMapping("/api/summernote/image")
    @ResponseBody
    public Result deleteImage(String fileUrl) {
        log.info("删除图片{}", fileUrl);
        fileUtil.deleteImgFromQiNiu(fileUrl);
        return ResultUtil.success();
    }


    @ApiOperation("获取七牛云服务器上的数据")
    @GetMapping("/api/image")
    @ResponseBody
    public Result getImagesUpload() {
        List<String> allImageUrl = fileUtil.getAllImgFromQiNiu();
        return ResultUtil.success(allImageUrl);
    }

}
