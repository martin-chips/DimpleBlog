package com.dimple.project.tool.qiNiu.domain;

import com.dimple.common.utils.StringUtils;
import lombok.Data;

/**
 * @className: QiNiuConfig
 * @description: 七牛云的配置
 * @author: Dimple
 * @date: 11/06/19
 */
@Data
public class QiNiuConfig {

    /**
     * 一个账号最多拥有两对密钥(Access/Secret Key)
     */
    private String accessKey;
    /**
     * 一个账号最多拥有两对密钥(Access/Secret Key)
     */
    private String secretKey;
    /**
     * 存储空间名称作为唯一的 Bucket 识别符
     */
    private String bucket;
    /**
     * Zone表示与机房的对应关系
     * 华东	Zone.zone0()
     * 华北	Zone.zone1()
     * 华南	Zone.zone2()
     * 北美	Zone.zoneNa0()
     * 东南亚	Zone.zoneAs0()
     */
    private String zone;
    /**
     * 外链域名，可自定义，需在七牛云绑定
     */
    private String host;
    /**
     * 空间类型：公开/私有
     */
    private String type = "公开";

    public boolean check() {
        return StringUtils.isNotEmpty(accessKey)
                && StringUtils.isNotEmpty(secretKey)
                && StringUtils.isNotEmpty(bucket)
                && StringUtils.isNotEmpty(zone)
                && StringUtils.isNotEmpty(host)
                && StringUtils.isNotEmpty(type);
    }
}
