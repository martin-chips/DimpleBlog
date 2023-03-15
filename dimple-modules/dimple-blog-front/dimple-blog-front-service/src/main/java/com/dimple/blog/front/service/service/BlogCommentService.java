package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogCommentBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogCommentService {

    List<BlogCommentBO> selectBlogCommentListWithSub(BlogCommentBO blogComment);


    /**
     * 新增
     *
     * @param blogComment
     * @return affected lines
     */
    int insertBlogComment(BlogCommentBO blogComment);

    int addBlogCommentLikeCount(Long id);

    Map<Long,Long> selectCommentCountByArticleIds(Set<Long> articleIds);
}
