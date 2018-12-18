package com.dimple.utils;

import com.dimple.framework.exception.file.FileNotExistException;
import com.dimple.framework.exception.file.FileNameLengthOutOfLimitException;
import com.dimple.framework.exception.file.FileTypeMisMatchException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

/**
 * @author : Dimple
 * @class: FileUploadUtil
 * @description : 文件操作工具类
 * @date: 12/13/18 13:51
 * @version: 1.0
 */
@Component
public class FileOperateUtil {

    @Value("${dimple.ImgUploadDir.windows}")
    private String WINDOWS_IMG_DIR = "";
    @Value("${dimple.ImgUploadDir.linux}")
    private String LINUX_IMG_DIR = "";
    //默认支持的文件类型
    @Value("${dimple.ImgUploadDir.allowImgFormat}")
    private String allowImgFormat;
    //图片上传地址
    private static String IMG_DIR = "";
    //默认大小50M
    private static final long DEFAULT_MAX_SIZE = 52428800;
    //默认文件上传的名字的长度
    private static final int DEFAULT_FILE_NAME_LENGTH = 200;


    @PostConstruct
    private void setInit() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            IMG_DIR = WINDOWS_IMG_DIR;
        } else {
            IMG_DIR = LINUX_IMG_DIR;
        }
    }

    /**
     * 图像上传
     *
     * @param file 图片文件
     * @return
     * @throws FileUploadBase.FileSizeLimitExceededException
     * @throws FileNameLengthOutOfLimitException
     * @throws FileTypeMisMatchException
     */
    public String imgUpload(MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException, FileNameLengthOutOfLimitException, FileTypeMisMatchException, FileNotExistException {
        if (file.isEmpty()) {
            throw new FileNotExistException("file can not be null!");
        }
        //文件长度校验
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > FileOperateUtil.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthOutOfLimitException(file.getOriginalFilename(), fileNameLength, FileOperateUtil.DEFAULT_FILE_NAME_LENGTH);
        }
        checkoutFileSize(file);
        String originalFilename = file.getOriginalFilename();
        //获取扩展名
        String extension = FilenameUtils.getExtension(originalFilename);
        if (!allowImgFormat.contains(extension.toLowerCase())) {
            throw new FileTypeMisMatchException(originalFilename, extension, allowImgFormat);
        }
        //生成随机名字
        String fileName = generateFileName(originalFilename, extension);
        //判断路径是否存在，不存在就建立一个文件夹
        File tempFile = new File(IMG_DIR);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(IMG_DIR + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/images/" + fileName;
    }

    /**
     * 获取图片名称以及上传的时间
     *
     * @return
     */
    public Map<String, Date> selectImagesNameAndModifyTime() {
        Map<String, Date> result = new HashMap<>();
        File file = new File(IMG_DIR);
        if (!file.exists()) {
            return null;
        }
        File[] files = getFilesOrderByModifyTime();
        for (File temp : files) {
            long l = temp.lastModified();
            Date date = new Date(l);
//            String name = temp.getName().length() < 9 ? temp.getName() : temp.getName().substring(0, 5) + temp.getName().substring(temp.getName().lastIndexOf("/"));
            result.put(temp.getName(), date);
        }
        return result;
    }


    /**
     * 根据URL对图片进行删除
     *
     * @param url
     * @return
     */
    public Boolean deleteImgByUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        int i = url.lastIndexOf("/");
        String uri = url.substring(i + 1);
        File file = new File(IMG_DIR + uri);
        if (!file.exists()) {
            return false;
        }
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取文件目录中所有的图片文件
     *
     * @return
     */
    public List<String> getAllImageUrl() {
        File file = new File(IMG_DIR);
        String[] list = file.list(filenameFilter);
        List<String> imageList = new LinkedList<>();
        for (String s : list) {
            imageList.add("/images/" + s);
        }
        return imageList;
    }

    /**
     * 获取所有的图片并按照创建时间排序
     *
     * @return
     */
    public List<String> getAllImageOrderByCreateTime() {
        File[] files = getFilesOrderByModifyTime();
        List<String> nameList = new LinkedList<>();
        for (int i = files.length - 1; i >= 0; i--) {
            nameList.add("/images/" + files[i].getName());
        }
        return nameList;
    }

    private File[] getFilesOrderByModifyTime() {
        File file = new File(IMG_DIR);
        File[] files = file.listFiles(filenameFilter);
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                long diff = file1.lastModified() - file2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }
        });
        return files;
    }

    /**
     * 根据图片的URl获取其真实的名字
     *
     * @param url 图片的URl
     * @return
     */
    public String getImgName(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        int i = url.lastIndexOf("/");
        return url.substring(i + 1);
    }

    /**
     * 生成文件名
     *
     * @param fileName  原始的名称
     * @param extension 扩展名
     * @return 生成的文件名
     */
    private static String generateFileName(String fileName, String extension) {
        fileName.replace("_", "");
        return Md5Util.hash(fileName) + "." + extension;
    }

    /**
     * 校验文件的大小是否合适
     *
     * @param file
     */
    private static void checkoutFileSize(MultipartFile file) throws FileUploadBase.FileSizeLimitExceededException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            throw new FileUploadBase.FileSizeLimitExceededException("file size above limit", size, DEFAULT_MAX_SIZE);
        }
    }

    /**
     * 设置路径
     *
     * @param dir      路径地址
     * @param filename 文件名称
     * @return
     * @throws IOException
     */
    private static final File getAbsoluteFile(String dir, String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 文件名称过滤器
     */
    private FilenameFilter filenameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            if (allowImgFormat.contains(FilenameUtils.getExtension(dir.getName()).toLowerCase())) {
                return true;
            }
            return false;
        }
    };

}
