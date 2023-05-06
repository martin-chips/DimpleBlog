package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogCommentBO;

import java.util.List;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogRestCommentService {

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
