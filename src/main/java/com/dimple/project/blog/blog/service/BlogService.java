package com.dimple.project.blog.blog.service;

import com.dimple.project.blog.blog.domain.Blog;

import java.util.List;

/**
 * @className: BlogService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface BlogService {
    /**
     * 根据条件查询所有的blog
     *
     * @param blog 带有条件信息的blog
     * @return 博客集合
     */
    List<Blog> selectBlogList(Blog blog);

    /**
     * 新增博客
     *
     * @param blog 需要新增的博客的实体
     * @return 受影响的行数
     */
    int insertBlog(Blog blog);

    /**
     * 根据id修改blog
     *
     * @param blogId 需要修改的blog的id
     * @return blog实体
     */
    Blog selectBlogById(Integer blogId);

    /**
     * 修改blog信息
     *
     * @param blog 需要修改的blog实体
     * @return 受影响的行数
     */
    int updateBlog(Blog blog);

    /**
     * 切换support状态
     *
     * @param blogIds 需要切换状态的blog的id
     * @param support 需要切换的support状态
     * @return 受影响的行数
     */
    int updateBlogSupportById(String blogIds, String support);

    /**
     * 切换博客的状态
     *
     * @param blogIds 博客的id
     * @param status  需要切换的博客的状态
     * @return 受影响的行数
     */
    int updateBlogStatusById(String blogIds, String status);

    /**
     * 删除博客
     *
     * @param ids 需要删除的blog的id
     * @return 受影响的行数
     */
    int deleteBlogById(Integer[] ids);
}
