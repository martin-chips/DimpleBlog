package com.dimple.blog.service.mapper;

import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.service.entity.BlogComment;

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
     * 查询列表
     *
     * @param blogComment
     * @return 集合
     */
    List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    List<BlogComment> selectBlogCommentByParentIds(List<Long> ids);

    List<KeyValue<Long, Long>> selectBlogCommentCountByArticleId(Set<Long> articleIds);

    /**
     * 修改
     *
     * @param blogComment
     * @return affected lines
     */
    int updateBlogComment(BlogComment blogComment);

    /**
     * 删除
     *
     * @param id 主键
     * @return affected lines
     */
    int deleteBlogCommentById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @return affected lines
     */
    int deleteBlogCommentByIds(Long[] ids);

    int addCommentLikeCount(Long id);

    int insertBlogComment(BlogComment blogComment);

}
