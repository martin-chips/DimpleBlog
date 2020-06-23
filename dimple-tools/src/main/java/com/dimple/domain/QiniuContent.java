package com.dimple.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @className: QiniuContent
 * @description: 上传成功后，存储结果
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Entity
@Table(name = "tool_qiniu_content")
public class QiniuContent implements Serializable {

    @Id
    @Column(name = "content_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "文件名")
    private String key;

    @ApiModelProperty(value = "空间名")
    private String bucket;

    @ApiModelProperty(value = "大小")
    private String size;

    @ApiModelProperty(value = "文件地址")
    private String url;

    @ApiModelProperty(value = "文件类型")
    private String suffix;

    @ApiModelProperty(value = "空间类型：公开/私有")
    private String type = "公开";

    @UpdateTimestamp
    @ApiModelProperty(value = "创建或更新时间")
    @Column(name = "update_time")
    private Timestamp updateTime;
}
