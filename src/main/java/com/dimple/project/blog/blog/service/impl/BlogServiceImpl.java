package com.dimple.project.blog.blog.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.common.utils.text.Convert;
import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.mapper.BlogMapper;
import com.dimple.project.blog.blog.mapper.BlogTagMapper;
import com.dimple.project.blog.blog.service.BlogService;
import com.dimple.project.blog.tag.service.TagService;
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
    @Autowired
    TagService tagService;
    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public List<Blog> selectBlogList(Blog blog) {
        return blogMapper.selectBlogList(blog);
    }

    @Override
    public int insertBlog(Blog blog) {
        blog.setCreateBy(ShiroUtils.getLoginName());
        int i = blogMapper.insertBlog(blog);
        handlerBlogTag(blog.getBlogId(), blog.getTags());
        return i;
    }

    void handlerBlogTag(int blogId, String[] tagTitles) {
        if (tagTitles == null || tagTitles.length == 0) {
            return;
        }
        //将tag数据插入到数据库
        tagService.insertTags(tagTitles);
        //根据tag的title获取id的集合
        List<Integer> tagIds = tagService.selectTagIdsByTagTitles(tagTitles);
        for (Integer id : tagIds) {
            blogTagMapper.insertBlogTag(id, blogId);
        }
    }

    @Override
    public Blog selectBlogById(Integer blogId) {
        return blogMapper.selectBlogById(blogId);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateBy(ShiroUtils.getLoginName());
        //添加新的tag
        tagService.insertTags(blog.getTags());
        updateBlogTag(blog.getBlogId(), blog.getTags());
        return blogMapper.updateBlog(blog);
    }

    private void updateBlogTag(Integer blogId, String[] tags) {
        //根据tag名称获取所有的tag的id
        List<Integer> ids = tagService.selectTagIdsByTagTitles(tags);
        //删除原有的tag的和blog的关联关系
        blogTagMapper.deleteBlogTagByBlogId(blogId);
        //重新建立两者的关系
        for (Integer id : ids) {
            blogTagMapper.insertBlogTag(id, blogId);
        }
    }

    @Override
    public int updateBlogSupportById(Integer blogId, String support) {
        return blogMapper.updateBlogSupportById(blogId, support);
    }

    @Override
    public int updateBlogStatusById(String blogIds, String status) {
        return blogMapper.updateBlogStatusByIds(Convert.toIntArray(blogIds),status);
    }

    @Override
    public int deleteBlogById(Integer[] ids) {
        return blogMapper.deleteBlogByIds(ids);
    }

    @Override
    public int selectBlogCountByStatus(int status) {
        return blogMapper.selectBlogCountByStatus(status);
    }

}
