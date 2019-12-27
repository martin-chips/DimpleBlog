package com.dimple.project.blog.service;

import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.front.domain.BlogQuery;

import java.util.List;

/**
 * @className: Blog
 * @description: 博客Service接口
 * @author: Dimple
 * @date: 2019-10-28
 */
public interface BlogService {
    /**
     * 查询博客
     *
     * @param id 博客ID
     * @return 博客
     */
    Blog selectBlogById(Long id);

    /**
     * 查询博客列表
     *
     * @param blog 博客
     * @return 博客集合
     */
    List<Blog> selectBlogList(Blog blog);

    /**
     * 新增博客
     *
     * @param blog 博客
     * @return 结果
     */
    int insertBlog(Blog blog);

    /**
     * 修改博客
     *
     * @param blog 博客
     * @return 结果
     */
    int updateBlog(Blog blog);

    /**
     * 批量删除博客
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBlogByIds(String ids);

    /**
     * 删除博客信息
     *
     * @param id 博客ID
     * @return 结果
     */
    int deleteBlogById(Long id);

    /**
     * 获取blog的标签
     *
     * @param query 查询条件
     * @return 标签名集合
     */
    List<String> selectBlogTagList(String query);

    /**
     * 前台查询blog列表
     *
     * @param blogQuery 查询条件
     * @return list
     */
    List<Blog> selectBlogList(BlogQuery blogQuery);

    /**
     * 获取前台显示的Blog
     *
     * @param id id
     * @return blog
     */
    Blog selectBlogDetailById(Long id);

    /**
     * 增加blog的like数量
     *
     * @param id blog id
     * @return 受影响的行数
     */
    int incrementBlogLike(Long id);

    /**
     * 获取blog对应的comment
     *
     * @param id id
     * @return list
     */
    List<Comment> selectBlogCommentListByBlogId(Long id);
}
