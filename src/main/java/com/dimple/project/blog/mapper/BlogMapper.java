package com.dimple.project.blog.mapper;

import com.dimple.project.blog.domain.Blog;
import com.dimple.project.front.domain.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: Blog
 * @description: 博客Mapper接口
 * @author: Dimple
 * @date: 2019-10-28
 */
public interface BlogMapper {
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
     * 删除博客
     *
     * @param id 博客ID
     * @return 结果
     */
    int deleteBlogById(@Param("id") Long id, @Param("username") String username);

    /**
     * 批量删除博客
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBlogByIds(@Param("ids") String[] ids, @Param("username") String username);

    /**
     * 前台查询blog
     *
     * @param blogQuery blog查询条件
     * @return list
     */
    List<Blog> selectBlogListQuery(BlogQuery blogQuery);

    /**
     * 新增blog的like
     *
     * @param id id
     * @return 受影响的行数
     */
    int incrementBlogLike(Long id);

    /**
     * 增加blog的click数量
     *
     * @param id id
     */
    void incrementBlogClick(Long id);

    /**
     * 查询博客
     *
     * @param id 博客ID
     * @return 博客
     */
    Blog selectBlogByIdQuery(Long id);

    /**
     * 根据categoryId获取所有的blog
     *
     * @param ids category ids
     * @return blog list
     */
    List<Blog> selectBlogListByCategoryIds(@Param("ids") List<Long> ids);
}
