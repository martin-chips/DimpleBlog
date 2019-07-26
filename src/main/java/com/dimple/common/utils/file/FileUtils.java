package com.dimple.common.utils.file;

import com.dimple.framework.config.SystemConfig;
import com.dimple.project.common.domain.FileItemInfo;
import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: FileUtils
 * @description: 文件处理工具类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class FileUtils {
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 生成文件名
     *
     * @param file 文件
     * @return 新生成的文件名
     */
    public static String generateFileName(MultipartFile file) {
        //获取文件后缀
        String fileExtension = Files.getFileExtension(file.getOriginalFilename());
        String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + "-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "." + fileExtension;
        return fileName;
    }

    /**
     * 获取所有图片
     *
     * @return 文件信息
     */
    public static List<FileItemInfo> getImageFileItemInfoList() {
        File file = new File(SystemConfig.getImagePath());
        File[] files = file.listFiles();
        List<FileItemInfo> fileItemInfos = Arrays.stream(files).map(f ->
                new FileItemInfo(f.getName(), String.valueOf(f.hashCode()), f.getUsableSpace(), "", new Date(f.lastModified()), FileItemInfo.ServerType.LOCAL.getServerType(), getImageFilePath(f.getName()))
        ).collect(Collectors.toList());
        return fileItemInfos;
    }

    /**
     * 根据文件名获取该文件的访问路径（图片）
     *
     * @param name 文件名
     * @return 可访问路径
     */
    public static String getImageFilePath(String name) {
        String imagePath = SystemConfig.getImagePath();
        return imagePath + "/" + name;
    }

    /**
     * 删除图片文件
     *
     * @param name 文件名
     * @return 是否删除成功
     */
    public static boolean deleteImageFile(String name) {
        boolean flag = false;
        String filePath = getImageFilePath(name);
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
