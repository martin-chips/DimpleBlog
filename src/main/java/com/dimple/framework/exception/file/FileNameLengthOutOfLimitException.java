package com.dimple.framework.exception.file;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 * @ClassName: FileNameLengthOutOfLimitException
 * @Description: 文件名称长度超过限制
 * @Auther: Owenb
 * @Date: 12/13/18 19:41
 * @Version: 1.0
 */
@Data
public class FileNameLengthOutOfLimitException extends FileUploadException {
    private int length;
    private long maxLength;
    private String fileName;

    public FileNameLengthOutOfLimitException(String fileName, int length, Long maxLength) {
        super("file name : [" + fileName + " ],length : [" + length + "],max length :[" + maxLength + "]");
        this.length = length;
        this.maxLength = maxLength;
        this.fileName = fileName;
    }
}
