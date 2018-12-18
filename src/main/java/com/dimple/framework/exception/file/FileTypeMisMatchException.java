package com.dimple.framework.exception.file;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 * @ClassName: FileTypeMisMatchException
 * @Description: 文件类型不匹配异常
 * @Auther: Owenb
 * @Date: 12/13/18 19:51
 * @Version: 1.0
 */
public class FileTypeMisMatchException extends FileUploadException {

    private String fileName;
    private String extension;
    private String allowImgFormat;

    public FileTypeMisMatchException(String fileName, String extension, String allowImgFormat) {
        super("file name : [" + fileName + " ],extension : [" + extension + "],allow Img format  :[" + allowImgFormat + "]");
        this.fileName = fileName;
        this.allowImgFormat = allowImgFormat;
        this.extension = extension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getAllowImgFormat() {
        return allowImgFormat;
    }
}
