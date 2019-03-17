package com.dimple.project.blog.blog.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import com.dimple.project.blog.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BlogServiceImpl
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> selectBlogList(Blog blog) {
        return blogMapper.selectBlogList(blog);
    }

    @Override
    public int insertBlog(Blog blog) {
        blog.setCreateBy(ShiroUtils.getLoginName());
        return blogMapper.insertBlog(blog);
    }

    @Override
    public Blog selectBlogById(Integer blogId) {
        return blogMapper.selectBlogById(blogId);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateBy(ShiroUtils.getLoginName());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int updateBlogSupportById(String blogIds, String support) {
        return blogMapper.updateBlogSupportByIds(blogIds,support);
    }

    @Override
    public int updateBlogStatusById(String blogIds, String status) {
        return blogMapper.updateBlogStatusById(blogIds,status);
    }

    @Override
    public int deleteBlogById(Integer[] ids) {
        return blogMapper.deleteBlogByIds(ids);
    }
}
