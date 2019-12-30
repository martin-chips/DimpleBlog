package com.dimple.common.utils.file;

/**
 * @className: MimeTypeUtils
 * @description: 媒体类型工具类
 * @author: Dimple
 * @date: 10/22/19
 */
public class MimeTypeUtils {
    private MimeTypeUtils() {
    }

    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    /**
     * 图片后缀
     */
    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png"};
    /**
     * Flash后缀
     */
    public static final String[] FLASH_EXTENSION = {"swf", "flv"};
    /**
     * 音视频后缀
     */
    public static final String[] MEDIA_EXTENSION = {"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb"};
    /**
     * 默认允许的类型
     */
    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf"
    };
}
