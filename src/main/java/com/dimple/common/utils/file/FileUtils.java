package com.dimple.common.utils.file;

import com.dimple.common.utils.IdUtils;
import com.dimple.common.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * @className: FileUtils
 * @description: 文件处理工具类
 * @author: Dimple
 * @date: 10/22/19
 */
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
            e.printStackTrace();
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
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if (image.contains(type)) {
            return "图片";
        } else if (documents.contains(type)) {
            return "文档";
        } else if (music.contains(type)) {
            return "音乐";
        } else if (video.contains(type)) {
            return "视频";
        } else {
            return "其他";
        }
    }

}
