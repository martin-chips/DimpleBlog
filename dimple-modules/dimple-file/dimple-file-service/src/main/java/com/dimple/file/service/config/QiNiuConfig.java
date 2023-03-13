package com.dimple.file.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * QiNiuConfig
 *
 * @author Dimple
 * @date 2023/3/13 13:34
 */
@Component
@ConfigurationProperties(prefix = "file.qiniu.config")
@Data
public class QiNiuConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String region;
    private String domain;
}
