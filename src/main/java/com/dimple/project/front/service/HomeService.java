package com.dimple.project.front.service;

import com.dimple.project.blog.blog.domain.Blog;

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
     * @return 最新的blog的集合
     */
    List<Blog> selectFrontNewestBlogList();
}
