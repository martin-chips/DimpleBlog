package com.dimple.common.utils.file;

import com.dimple.common.exception.file.FileNameLengthLimitExceededException;
import com.dimple.common.exception.file.FileSizeLimitExceededException;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.framework.config.ServerConfig;
import com.dimple.framework.config.SystemConfig;
import com.dimple.project.common.domain.FileItemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @className: FileUploadUtils
 * @description: 文件上传工具类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = SystemConfig.getProfile();

    /**
     * 系统配置：用于获取系统的绝对访问逻辑
     */
    private static ServerConfig serverConfig = SpringUtils.getBean(ServerConfig.class);

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件
     * @throws Exception
     */
    public static final FileItemInfo upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }


    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 返回上传成功的文件
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     */
    public static final FileItemInfo upload(String baseDir, MultipartFile file)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {

        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file);

        String fileName = FileUtils.generateFileName(file);

        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        FileItemInfo fileItemInfo = new FileItemInfo(fileName, String.valueOf(file.hashCode()), file.getSize(), file.getContentType(), new Date(), FileItemInfo.ServerType.LOCAL.getServerType(), baseDir + "/" + fileName);

        return fileItemInfo;
    }


    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }
    }

}
