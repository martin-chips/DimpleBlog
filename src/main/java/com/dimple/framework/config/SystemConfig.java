package com.dimple.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: Config
 * @description: 读取项目相关配置
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Component
@ConfigurationProperties(prefix = "dimple")
public class SystemConfig {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 版权年份
     */
    private String copyrightYear;
    /**
     * 上传路径
     */
    private static String profile;
    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        SystemConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        SystemConfig.addressEnabled = addressEnabled;
    }

    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    public static String getDownloadPath() {
        return profile + "download/";
    }

    public static String getUploadPath() {
        return profile + "upload/";
    }

    public static String getImagePath() {
        return profile + "images/";
    }
}
