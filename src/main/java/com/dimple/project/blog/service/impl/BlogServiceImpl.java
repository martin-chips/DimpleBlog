package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.blog.domain.Tag;
import com.dimple.project.blog.mapper.BlogMapper;
import com.dimple.project.blog.mapper.BlogTagMapper;
import com.dimple.project.blog.mapper.CommentMapper;
import com.dimple.project.blog.mapper.TagMapper;
import com.dimple.project.blog.service.BlogService;
import com.dimple.project.front.domain.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @className: Blog
 * @description: 博客Service业务层处理
 * @author: Dimple
 * @date: 2019-10-28
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public Blog selectBlogById(Long id) {
        Blog blog = blogMapper.selectBlogById(id);
        blog.setTagList(tagMapper.selectTagListByBlogId(id));
        return blog;
    }

    @Override
    public List<Blog> selectBlogList(Blog blog) {
        List<Blog> blogList = blogMapper.selectBlogList(blog);
        if (blogList.isEmpty()) {
            return blogList;
        }
        List<Long> blogIdList = blogList.stream().map(e -> e.getId()).collect(Collectors.toList());
        //设置comment信息
        List<Comment> commentList = commentMapper.selectCommentListByPageId(blogIdList);
        for (Blog temp : blogList) {
            temp.setCommentList(commentList.stream().filter(e -> e.getPageId().equals(temp.getId())).collect(Collectors.toList()));
            List<Tag> tagList = tagMapper.selectTagListByBlogId(temp.getId());
            temp.setTagList(tagList);
        }
        return blogList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int insertBlog(Blog blog) {
        blog.setCreateBy(SecurityUtils.getUsername());
        int count = blogMapper.insertBlog(blog);
        updateBlogTagMapping(blog);
        return count;
    }

    /**
     * 设置blog和tag的关联
     */
    private void updateBlogTagMapping(Blog blog) {
        //删除该blogId下的所有关联
        blogTagMapper.deleteBlogTagByBlogId(blog.getId());
        List<Tag> tagList = blog.getTagList();
        if (ObjectUtils.isNotEmpty(tagList)) {
            for (Tag tag : tagList) {
                //如果有id,直接插入关联表
                if (ObjectUtils.isNotEmpty(tag.getId())) {
                    blogTagMapper.insertBlogTag(blog.getId(), tag.getId());
                } else {
                    //创建tag
                    Tag temp = new Tag();
                    temp.setTitle(tag.getTitle());
                    temp.setColor(StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 0.5));
                    temp.setCreateBy(SecurityUtils.getUsername());
                    tagMapper.insertTag(tag);
                    blogTagMapper.insertBlogTag(blog.getId(), temp.getId());
                }
            }
        }
    }

    /**
     * 获取随机数
     *
     * @param num 最大范围
     * @return 随机数
     */
    private int getRandomNum(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateBy(SecurityUtils.getUsername());
        int count = blogMapper.updateBlog(blog);
        updateBlogTagMapping(blog);
        return count;
    }

    @Override
    public int deleteBlogByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return blogMapper.deleteBlogByIds(ConvertUtils.toStrArray(ids), username);
    }

    @Override
    public int deleteBlogById(Long id) {
        String username = SecurityUtils.getUsername();
        return blogMapper.deleteBlogById(id, username);
    }

    @Override
    public List<Tag> selectBlogTagList(String query) {
        Tag tag = new Tag();
        tag.setTitle(query);
        List<Tag> tagList = tagMapper.selectTagList(tag);
        return tagList;
    }

    @Override
    public List<Blog> selectBlogList(BlogQuery blogQuery) {
        return blogMapper.selectBlogListQuery(blogQuery);
    }
}
