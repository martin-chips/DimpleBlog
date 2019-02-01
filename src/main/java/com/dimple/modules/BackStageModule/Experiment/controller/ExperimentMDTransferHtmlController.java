package com.dimple.modules.BackStageModule.Experiment.controller;

import com.dimple.framework.exception.file.FileNameLengthOutOfLimitException;
import com.dimple.framework.exception.file.FileNotExistException;
import com.dimple.framework.exception.file.FileTypeMisMatchException;
import com.dimple.framework.message.Result;
import com.dimple.modules.BackStageModule.Experiment.service.ExperimentMdService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : ExperimentMDTransferHtmlController
 * @description :
 * @date : 01/09/19 12:38
 */
@Controller
public class ExperimentMDTransferHtmlController {

    @Autowired
    ExperimentMdService experimentMdService;

    @GetMapping("/page/experiment/md.html")
    @ApiOperation("实验室——批量转换博文")
    @RequiresPermissions("page:experimentMd:view")
    public String transferMd() {
        return "experiment/mdTransferHtml";
    }

    @PostMapping("/api/experiment/mdUpload")
    @ApiOperation("处理MD文章")
    @RequiresPermissions("page:experimentMd:insert")
    @ResponseBody
    public Result mdUploadTransferHtml(@RequestParam("file") MultipartFile file) throws FileNameLengthOutOfLimitException, FileUploadBase.FileSizeLimitExceededException, FileTypeMisMatchException, FileNotExistException {

        return experimentMdService.handlerMdFile(file);
    }
}
