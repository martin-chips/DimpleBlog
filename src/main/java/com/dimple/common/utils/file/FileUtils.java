package com.dimple.common.utils.file;

import com.dimple.common.exception.file.FileNameLengthLimitExceededException;
import com.dimple.common.exception.file.FileSizeLimitExceededException;
import com.dimple.common.exception.file.InvalidExtensionException;
import com.dimple.common.utils.IdUtils;
import com.dimple.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @className: FileUtils
 * @description: 文件处理工具类
 * @author: Dimple
 * @date: 10/22/19
 */
@Slf4j
public class FileUtils {
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;
    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * 默认最大大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 256
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 256;

    /**
     * 将multipartFile转为file
     *
     * @param multipartFile multipartFile
     * @return File
     */
    public static File toFile(MultipartFile multipartFile) {
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        String prefix = "." + getExtensionName(fileName);
        File file = null;
        try {
            //创建UUID作为文件名,防止临时文件名冲突
            file = File.createTempFile(IdUtils.simpleUUID(), prefix);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * 获取文件的后缀名,不带"."
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getExtensionName(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            int dot = fileName.lastIndexOf(".");
            if (dot > -1 && dot < (fileName.length() - 1)) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    /**
     * 获取文件名称,不带后缀
     *
     * @param fileName 文件名
     * @return 文件名(不带后缀)
     */
    public static String getFileNameNoExtension(String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            int dot = fileName.lastIndexOf(".");
            if (dot > -1 && dot < fileName.length()) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;
    }

    /**
     * 大小转换,将字节大小转为对应的单位
     *
     * @param size 字节大小
     * @return 转换后的大小
     */
    public static String getSizeString(long size) {
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB + "GB");
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B";
        }
        return resultSize;
    }

    /**
     * InputStream转为文件
     *
     * @param ins  inputStream
     * @param name 文件名
     * @return File
     * @throws Exception
     */
    public static File inputStreamToFile(InputStream ins, String name) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return file;
    }

    /**
     * 获取文件类型
     *
     * @param type 文件名
     * @return 文件类型
     */
    public static String getFileType(String type) {
        String documents = "txt doc pdf ppt pps xlsx xls docx";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg ico";
        if (image.contains(type)) {
            return "image";
        } else if (documents.contains(type)) {
            return "document";
        } else if (music.contains(type)) {
            return "music";
        } else if (video.contains(type)) {
            return "video";
        } else {
            return "other";
        }
    }

    /**
     * 上传文件
     *
     * @param file             文件
     * @param filePath         文件路径
     * @param allowedExtension 允许的类型
     * @return 文件
     * @throws InvalidExtensionException 类型校验失败
     */
    public static File upload(MultipartFile file, String filePath, String[] allowedExtension) throws InvalidExtensionException {
        checkAllowExtension(file, allowedExtension);
        checkFileSize(file);
        return upload(file, filePath);
    }

    /**
     * 检验文件的大小
     *
     * @param file 文件
     */
    private static void checkFileSize(MultipartFile file) {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }
    }

    /**
     * 校验上传文件类型
     *
     * @param file             已经上传的文件
     * @param allowedExtension 允许的类型
     */
    private static void checkAllowExtension(MultipartFile file, String[] allowedExtension) throws InvalidExtensionException {
        String fileName = file.getOriginalFilename();
        String extension = getExtensionName(fileName);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    private static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 上传文件到指定路径
     *
     * @param file     文件
     * @param filePath 路径
     * @return 文件信息
     */
    public static File upload(MultipartFile file, String filePath) {
        //校验文件名大小
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(DEFAULT_FILE_NAME_LENGTH);
        }

        String name = getFileNameNoExtension(file.getOriginalFilename());
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
        try {
            String fileName = name + nowStr + "." + suffix;
            String path = filePath + fileName;
            //getCanonicalPath会将文件路径解析为与操作系统相关的唯一的规范形式的字符串，而getAbsolutePath并不会
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (IOException e) {
            log.error("上传文件出错,{}", e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件<br>
     *
     * @param file 文件对象
     * @return 成功与否
     * @throws RuntimeException IO异常
     */
    public static boolean del(File file) throws RuntimeException {
        if (file == null || false == file.exists()) {
            // 如果文件不存在或已被删除，此处返回true表示删除成功
            return true;
        }
        if (file.isDirectory()) {
            return false;
        }
        // 删除文件或清空后的目录
        return file.delete();
    }

    /**
     * 删除文件<br>
     * 路径如果为相对路径，会转换为ClassPath路径！<br>
     * 某个文件删除失败会终止删除操作
     *
     * @param fullFileOrDirPath 文件或者目录的路径
     * @return 成功与否
     * @throws RuntimeException IO异常
     */
    public static boolean del(String fullFileOrDirPath) throws RuntimeException {
        return del(file(fullFileOrDirPath));
    }

    /**
     * 创建File对象
     *
     * @param path 文件路径
     * @return File
     */
    public static File file(String path) {
        if (null == path) {
            return null;
        }
        return new File(path);
    }

}
