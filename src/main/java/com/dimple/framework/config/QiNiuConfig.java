package com.dimple.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Dimple
 * @version : 1.0
 * @class :
 * @description : the setting key and value for file utils
 * @date : 01/07/19 10:40
 */
@Data
@Component
@ConfigurationProperties(prefix = "dimple.qiniuyun")
public class QiNiuConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String path;
    private Long imageMaxSize;
    private Long imageMaxNameLength;
}
