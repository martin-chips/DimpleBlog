package com.dimple.modules.endModule.experiment.service;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.framework.exception.file.FileNameLengthOutOfLimitException;
import com.dimple.framework.exception.file.FileNotExistException;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.service.BlogService;
import com.dimple.utils.FileUtil;
import com.dimple.utils.MessageUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : ExperimentMdServiceImpl
 * @description :
 * @date : 01/09/19 12:51
 */
@Service
@Transactional
public class ExperimentMdServiceImpl implements ExperimentMdService {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    BlogService blogService;

    @Override
    public Result handlerMdFile(MultipartFile file) {
        try {
            Blog blog = fileUtil.markdownTransferToBlog(file);
            blog.setStatus(2);
            blog.setSupport(false);
            blog.setClick(0);
            blog.setSummary(blog.getContent().length() < 150 ? blog.getContent() : blog.getContent().substring(0, 150));
            blogService.insertBlog(blog);

        } catch (FileNotExistException e) {
            e.printStackTrace();
            return ResultUtil.error(500, MessageUtil.getMessage("file.fileNotExists"));
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            return ResultUtil.error(500, MessageUtil.getMessage("file.sizeOutOfLimit"));
        } catch (FileNameLengthOutOfLimitException e) {
            e.printStackTrace();
            return ResultUtil.error(500, MessageUtil.getMessage("file.sizeOutOfLimit"));
        }
        return null;
    }
}
