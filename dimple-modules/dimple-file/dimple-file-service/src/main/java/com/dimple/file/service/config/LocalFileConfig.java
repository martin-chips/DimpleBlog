package com.dimple.file.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * LocalFileConfig
 *
 * @author Dimple
 * @date 2023/3/13 13:45
 */
@Component
@ConfigurationProperties(prefix = "file.local.config")
@Data
public class LocalFileConfig {
    private String domain;
    private String path;
    private String prefix;
}
