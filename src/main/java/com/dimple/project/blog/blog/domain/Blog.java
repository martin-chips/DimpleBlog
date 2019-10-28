package com.dimple.project.blog.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Blog
 * @description:
 * @author: Dimple
 * @date: 10/24/19
 */
@Data
public class Blog extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 封面图片地址
     */
    private String headerImg;
    /**
     * 正文内容
     */
    private String content;
    /**
     * 文章状态,true表示已经发表,false表示草稿箱
     */
    private Boolean status;
    /**
     * 是否允许评论,true表示允许,false表示不允许
     */
    private Boolean comment;

}
