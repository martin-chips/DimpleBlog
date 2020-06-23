package com.dimple.config;

import com.dimple.utils.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: FileProperties
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * 文件大小限制
     */
    private Long maxSize;

    /**
     * 头像大小限制
     */
    private Long avatarMaxSize;

    private FilePath mac;

    private FilePath linux;

    private FilePath windows;

    public FilePath getPath() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith(Constant.WIN)) {
            return windows;
        } else if (os.toLowerCase().startsWith(Constant.MAC)) {
            return mac;
        }
        return linux;
    }

    @Data
    public static class FilePath {

        private String path;

        private String avatar;
    }
}
