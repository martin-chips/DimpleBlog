package com.dimple.project.blog.comment.mapper;

import com.dimple.project.blog.comment.domain.Comment;

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
}
