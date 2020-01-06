package com.dimple.common.exception.file;

/**
 * @className: FileSizeLimitExceededException
 * @description: if the size of this file are gt than the maxSize,will throw this exception
 * @author: Dimple
 * @date: 10/22/19
 */
public class FileSizeLimitExceededException extends FileException {

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
