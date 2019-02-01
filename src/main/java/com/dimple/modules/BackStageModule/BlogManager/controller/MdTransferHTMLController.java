package com.dimple.modules.BackStageModule.BlogManager.controller;

import com.dimple.framework.exception.file.FileNameLengthOutOfLimitException;
import com.dimple.framework.exception.file.FileNotExistException;
import com.dimple.framework.exception.file.FileTypeMisMatchException;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: ExperimentController
 * @Description: 实验室功能的Controller MD 转为HTML
 * @Auther: Dimple
 * @Date: 2019/1/5 15:18
 * @Version: 1.0
 */
@Controller
public class MdTransferHTMLController {

    @Autowired
    FileUtil fileUtil;

    @GetMapping("/page/mdTransferHtml.html")
    public String mdTransferHtmlPage() {
        return "blog/mdUpload";
    }

    @PostMapping("/api/blog/mdUpload")
    @ResponseBody
    public Result mdUploadTransferHtml(@RequestParam("file") MultipartFile file) throws FileNameLengthOutOfLimitException, FileUploadBase.FileSizeLimitExceededException, FileTypeMisMatchException, FileNotExistException {
        return ResultUtil.success(fileUtil.markdownTransferToHtml(file));
    }
}
