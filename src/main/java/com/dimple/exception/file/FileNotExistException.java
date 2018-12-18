package com.dimple.exception.file;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 * @ClassName: FileNotExistException
 * @Description:
 * @Auther: Owenb
 * @Date: 12/17/18 13:43
 * @Version: 1.0
 */
public class FileNotExistException extends FileUploadException {

    public FileNotExistException(String message) {
        super(message);
    }
}
