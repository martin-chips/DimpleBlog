package com.dimple.common.exception.file;

/**
 * @className: FileNameLengthLimitExceededException
 * @description: 文件名称超长限制异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class FileNameLengthLimitExceededException extends FileException {

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
