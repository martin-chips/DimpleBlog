package com.dimple.project.front.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: BlogQuery
 * @description: 博客前端查询, 通用的查询(分页等)已经做了封装处理, 这里只需要关注具体的业务
 * @author: Dimple
 * @date: 11/21/19
 */
@Data
public class BlogQuery implements Serializable {
    /**
     * 分类Id
     */
    private Long categoryId;
    /**
     * 是否推荐
     */
    private Boolean support;
    /**
     * 查询开始时间
     */
    private Date beginTime;
    /**
     * 查询结束时间
     */
    private Date endTime;
}
