package com.dimple.project.blog.mapper;

import com.dimple.project.blog.domain.Comment;

import java.util.List;

/**
 * @className: CommentMapper
 * @description:
 * @author: Dimple
 * @date: 2019-10-28
 */
public interface CommentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment selectCommentById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param Comment 实例对象
     * @return 对象列表
     */
    List<Comment> selectCommentList(Comment Comment);

    /**
     * 新增数据
     *
     * @param Comment 实例对象
     * @return 影响行数
     */
    int insertComment(Comment Comment);

    /**
     * 修改数据
     *
     * @param Comment 实例对象
     * @return 影响行数
     */
    int updateComment(Comment Comment);

    /**
     * 通过主键删除数据
     *
     * @param id       主键
     * @param username
     * @return 影响行数
     */
    int deleteCommentById(Long id, String username);

    /**
     * 评论点赞
     *
     * @param id
     * @return 受影响的行数
     */
    int incrementCommentGood(Long id);

    /**
     * 评论踩
     *
     * @param id
     * @return 受影响的行数
     */
    int incrementCommentBad(Long id);

}
