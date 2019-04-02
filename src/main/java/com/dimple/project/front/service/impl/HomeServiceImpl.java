package com.dimple.project.front.service.impl;

import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import com.dimple.project.front.domain.Archives;
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
    public List<Blog> selectFrontBlogList(Blog blog) {
        List<Blog> blogs = blogMapper.selectFrontBlogList(blog);
        return blogs;
    }

    @Override
    public List<Archives> selectArchives() {
        List<Archives> archives = blogMapper.selectArchivesDateAndCount();
        for (Archives archive : archives) {
            archive.setBlogs(blogMapper.selectBlogByCreateTime(archive.getDate()));
        }
        return archives;
    }
}
