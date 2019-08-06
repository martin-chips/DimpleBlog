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

}
