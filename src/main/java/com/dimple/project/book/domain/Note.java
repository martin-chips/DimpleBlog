package com.dimple.project.book.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @className: Note
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
@Data
public class Note extends BaseEntity implements Serializable {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String summary;
    /**
     * 内容
     */
    private String content;
    /**
     * HTML 格式化后的内容
     */
    private String htmlContent;
    /**
     * 点击量
     */
    private Long click;
    /**
     * 点赞
     */
    private Long like;
    /**
     * 权重
     */
    private Long weight;
    /**
     * 所属章节
     */
    private String chapter;
    /**
     * 图书Id
     */
    private Long bookId;
    /**
     * 封面
     */
    private String headerImg;

    private Integer headerImgType;
    /**
     * 推荐
     */
    private Boolean support;
    /**
     * 评论
     */
    private Boolean comment;
    /**
     * true表示发表,false表示草稿
     */
    private Boolean status;

    private List<String> tagTitleList;


}
