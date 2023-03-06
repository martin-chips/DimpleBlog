package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogCommentBO;

import java.util.List;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogCommentService {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogCommentBO selectBlogCommentById(Long id);

    /**
     * 查询列表
     *
     * @param blogComment
     * @return 集合
     */
    List<BlogCommentBO> selectBlogCommentList(BlogCommentBO blogComment);


    List<BlogCommentBO> selectBlogCommentListWithSub(BlogCommentBO blogComment);


    /**
     * 新增
     *
     * @param blogComment
     * @return affected lines
     */
    int insertBlogComment(BlogCommentBO blogComment);

    int addBlogCommentLikeCount(Long id);
}
