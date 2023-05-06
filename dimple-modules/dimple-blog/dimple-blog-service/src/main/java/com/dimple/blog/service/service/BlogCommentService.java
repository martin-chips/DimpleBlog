package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.api.bo.KeyValue;

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

    List<BlogCommentBO> selectBlogCommentByIds(List<Long> id);

    List<KeyValue<Long, Long>> selectBlogCommentCountByArticleId(List<Long> ids);

    /**
     * 查询列表
     *
     * @param blogComment
     * @return 集合
     */
    List<BlogCommentBO> selectBlogCommentList(BlogCommentBO blogComment);

    /**
     * 修改
     *
     * @param blogComment
     * @return affected lines
     */
    int updateBlogComment(BlogCommentBO blogComment);

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return affected lines
     */
    int deleteBlogCommentByIds(Long[] ids);

    /**
     * 删除信息
     *
     * @param id 主键
     * @return affected lines
     */
    int deleteBlogCommentById(Long id);

    int insertBlogComment(BlogCommentBO blogComment);

    int addCommentLikeCount(Long id);
}
