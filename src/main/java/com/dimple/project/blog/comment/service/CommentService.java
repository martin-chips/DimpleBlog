package com.dimple.project.blog.comment.service;

import com.dimple.project.blog.comment.domain.Comment;

import java.util.List;

/**
 * @className: CommentService
 * @description:
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
public interface CommentService {

    List<Comment> selectCommentList(Comment comment);

    int deleteCommentById(Integer id);

    int insertComment(Comment comment);

    /**
     * 增加点赞数
     *
     * @param pageId    页面id
     * @param commentId 评论的id
     */
    int increaseGoodCount(Integer pageId, Integer commentId);

    /**
     * 增加踩数
     *
     * @param pageId
     * @param commentId
     * @return
     */
    int increaseBadCount(Integer pageId, Integer commentId);
}
