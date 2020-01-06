package com.dimple.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: DimpleBlogConfig
 * @description: get project setting
 * @author: Dimple
 * @date: 10/22/19
 */
@Component
@ConfigurationProperties(prefix = "dimple")
public class DimpleBlogConfig {
    /**
     * application name
     */
    private String name;

    /**
     * application version
     */
    private String version;

    /**
     * copyright
     */
    private String copyrightYear;

    /**
     * upload url
     */
    private static String profile;

    /**
     * enable address get
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
        DimpleBlogConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        DimpleBlogConfig.addressEnabled = addressEnabled;
    }
}
