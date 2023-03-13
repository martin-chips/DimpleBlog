package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogComment;
import com.dimple.blog.front.service.entity.KeyValue;

import java.util.List;
import java.util.Set;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogCommentMapper {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogComment selectBlogCommentById(Long id);

    /**
     * 新增
     *
     * @param blogComment
     * @return affected lines
     */
    int insertBlogComment(BlogComment blogComment);

    /**
     * 查询列表
     *
     * @param blogComment
     * @return 集合
     */
    List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    List<BlogComment> selectBlogCommentByParentIds(List<Long> ids);

    int addCommentLikeCount(Long id);

    List<KeyValue<Long, Long>> selectBlogCommentCountByArticleId(Set<Long> articleIds);
}
