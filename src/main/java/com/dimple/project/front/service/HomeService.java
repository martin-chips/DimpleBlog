package com.dimple.project.front.service;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.front.domain.Archives;

import java.util.List;

/**
 * @className: HomeService
 * @description:
 * @auther: Dimple
 * @date: 03/27/19
 * @version: 1.0
 */
public interface HomeService {
    /**
     * 获取最新的blog
     *
     * @param blog
     * @return 最新的blog的集合
     */
    List<Blog> selectFrontBlogList(Blog blog);

    /**
     * 获取归档信息
     *
     * @return 归档集合
     */
    List<Archives> selectArchives();
}
