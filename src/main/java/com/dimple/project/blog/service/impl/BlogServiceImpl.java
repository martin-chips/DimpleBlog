package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.BlogTag;
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
        blog.setTagTitleList(getTagTitleListByBlogId(id));
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
        List<Comment> commentList = commentMapper.selectCommentListByPageIds(blogIdList);
        for (Blog temp : blogList) {
            temp.setCommentList(commentList.stream().filter(e -> e.getPageId().equals(temp.getId())).collect(Collectors.toList()));
            temp.setTagTitleList(getTagTitleListByBlogId(temp.getId()));
        }
        return blogList;
    }

    /**
     * 根据id获取title集合
     *
     * @param blogId blog的id
     * @return title集合
     */
    private List<String> getTagTitleListByBlogId(Long blogId) {
        List<Tag> tagList = tagMapper.selectTagListByBlogId(blogId);
        List<String> tagTitleList = tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
        return tagTitleList;
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
        List<String> tagTitleList = blog.getTagTitleList();
        if (ObjectUtils.isNotEmpty(tagTitleList)) {
            for (String title : tagTitleList) {
                //搜索所有的该tag
                Tag tag = tagMapper.selectTagByTitle(title);
                if (tag != null) {
                    blogTagMapper.insertBlogTag(new BlogTag(blog.getId(), tag.getId()));
                } else {
                    Tag temp = new Tag(title, StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 1));
                    tagMapper.insertTag(temp);
                    blogTagMapper.insertBlogTag(new BlogTag(blog.getId(), temp.getId()));
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
    public List<String> selectBlogTagList(String query) {
        Tag tag = new Tag();
        tag.setTitle(query);
        List<Tag> tagList = tagMapper.selectTagList(tag);
        return tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<Blog> selectBlogList(BlogQuery blogQuery) {
        List<Blog> blogList = blogMapper.selectBlogListQuery(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(tagMapper.selectTagListByBlogId(blog.getId()));
        }
        return blogList;
    }

    @Override
    public Blog selectBlogDetailById(Long id) {
        Blog blog = blogMapper.selectBlogByIdQuery(id);
        blog.setTagList(tagMapper.selectTagListByBlogId(id));
        //设置评论
        List<Comment> commentList = getCommentListByPageId(id);
        blog.setCommentList(commentList);
        //设置点击数量+1
        blogMapper.incrementBlogClick(id);
        return blog;
    }

    /**
     * 获取评论
     *
     * @param id id
     * @return 评论列表
     */
    private List<Comment> getCommentListByPageId(Long id) {
        List<Comment> commentList = commentMapper.selectCommentListByPageId(id);
        for (Comment comment : commentList) {
            if (comment.getParentId() != null) {
                comment.setSubComment(commentMapper.selectCommentById(comment.getPageId()));
            }
        }
        return commentList;
    }

    @Override
    public int incrementBlogLike(Long id) {
        return blogMapper.incrementBlogLike(id);
    }

    @Override
    public List<Comment> selectBlogCommentListByBlogId(Long id) {
        commentMapper.selectCommentListByPageId(id);
        return null;
    }
}
