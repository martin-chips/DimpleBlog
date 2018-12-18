package com.dimple.framework.exception.file;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 * @ClassName: FileNameLengthOutOfLimitException
 * @Description: 文件名称长度超过限制
 * @Auther: Owenb
 * @Date: 12/13/18 19:41
 * @Version: 1.0
 */
public class FileNameLengthOutOfLimitException extends FileUploadException {
    private int length;
    private int maxLength;
    private String fileName;

    public FileNameLengthOutOfLimitException(String fileName, int length, int maxLength) {
        super("file name : [" + fileName + " ],length : [" + length + "],max length :[" + maxLength + "]");
        this.length = length;
        this.maxLength = maxLength;
        this.fileName = fileName;
    }

    public int getLength() {
        return length;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getFileName() {
        return fileName;
    }
}
