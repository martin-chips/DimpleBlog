package com.dimple.project.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Tag
 * @description:
 * @author: Dimple
 * @date: 01/21/20
 */
@Data
public class ReplayEmail implements Serializable {
    /**
     * reply Create time
     */
    private Date createTime;
    /**
     * blog title
     */
    private String title;
    /**
     * reply name
     */
    private String replyName;
    /**
     * name
     */
    private String name;
    /**
     * origin content
     */
    private String originContent;
    /**
     * reply content
     */
    private String replyContent;
    /**
     * url
     */
    private String url;
    /**
     * header img
     */
    private String headerImg;

    public Map<String, Object> toMap() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("createTime", simpleDateFormat.format(createTime));
        map.put("title", title);
        map.put("replyName", replyName);
        map.put("originContent", originContent);
        map.put("replyContent", replyContent);
        map.put("url", url);
        return map;
    }

}
