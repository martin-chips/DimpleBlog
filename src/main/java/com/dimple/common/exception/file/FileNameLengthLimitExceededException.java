package com.dimple.common.exception.file;

/**
 * @className: FileNameLengthLimitExceededException
 * @description: 文件名称超长限制异常类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
