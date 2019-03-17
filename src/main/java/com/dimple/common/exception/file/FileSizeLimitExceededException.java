package com.dimple.common.exception.file;

/**
 * @className: FileSizeLimitExceededException
 * @description: 文件名大小限制异常类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
