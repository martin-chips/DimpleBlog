package com.dimple.project.front.domain;

import com.dimple.project.blog.blog.domain.Blog;
import lombok.Data;

import java.util.List;

/**
 * @className: Archives
 * @description: 归档VO
 * @auther: Dimple
 * @date: 04/02/19
 * @version: 1.0
 */
@Data
public class Archives {
    private String date;
    private Integer count;
    private List<Blog> blogs;
}
