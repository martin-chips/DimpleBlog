package com.dimple.modules.endModule.blogManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @className: BlogTag
 * @description:
 * @auther: Owenb
 * @date: 01/28/19
 * @version: 1.0
 */
@Entity
@Data
@Table(name = "blog_tag", schema = "dimple_blog", catalog = "")
public class BlogTag {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer blogId;
    private Integer tagId;
}
