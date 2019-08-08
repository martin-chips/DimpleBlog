package com.dimple.project.blog.comment.mapper;

import com.dimple.project.blog.comment.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: CommentMapper
 * @description:
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
public interface CommentMapper {

    List<Comment> selectCommentList(Comment comment);

    int deleteCommonById(Integer id);

    int insertComment(Comment comment);

    Comment selectCommentById(Integer replyId);

    int increaseGoodCount(@Param("pageId") Integer pageId, @Param("commentId") Integer commentId);

    int increaseBadCount(@Param("pageId") Integer pageId, @Param("commentId") Integer commentId);

    /**
     * 批量删除留言
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBgCommentByIds(String[] ids);

}
