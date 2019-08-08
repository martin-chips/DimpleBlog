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
    /**
     * 获取前端展示的Comment
     *
     * @param comment comment
     * @return CommentList
     */
    List<Comment> selectCommentListForFront(Comment comment);

    /**
     * 获取CommentList
     *
     * @param comment 带有条件的Comment
     * @return Comment List
     */
    List<Comment> selectCommentList(Comment comment);

    int deleteCommentById(Integer id);

    /**
     * 批量删除留言
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBgCommentByIds(String ids);

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

    /**
     * 根据Id查询单个comment
     *
     * @param id id
     * @return Comment
     */
    Comment selectCommentById(Integer id);

    /**
     * 修改显示状态
     *
     * @param id
     * @param display
     * @return
     */
    int changeDisplayById(Integer id, boolean display);

}
