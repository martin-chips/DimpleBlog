package com.dimple.common.exception.file;

/**
 * @className: FileSizeLimitExceededException
 * @description: 文件名大小限制异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class FileSizeLimitExceededException extends FileException {

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
