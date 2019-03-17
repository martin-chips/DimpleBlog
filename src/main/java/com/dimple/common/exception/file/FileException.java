package com.dimple.common.exception.file;

import com.dimple.common.exception.base.BaseException;

/**
 * @className: FileException
 * @description: 文件信息异常类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
