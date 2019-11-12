package com.dimple.framework.web.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: BaseEntity
 * @description: Entity基类
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新者
     */
    private String deleteBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;

    /**
     * 备注
     */
    private String remark;


    /**
     * 请求参数
     */
    @JsonIgnore
    private String params;

    @SuppressWarnings("unchecked")
    public Map<String, Object> getParams() {
        if (params == null) {
            return new HashMap<>();
        }
        return JSON.parseObject(params, Map.class);
    }

    //更新实体的分组
    public interface Update {
    }
}

