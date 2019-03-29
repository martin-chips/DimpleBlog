package com.dimple.project.front.service.impl;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import com.dimple.project.front.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: HomeServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 03/27/19
 * @version: 1.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> selectFrontNewestBlogList() {
        List<Blog> blogs = blogMapper.selectFrontNewestBlogList();
        return blogs;
    }
}
