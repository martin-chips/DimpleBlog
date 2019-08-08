package com.dimple.project.blog.comment.service.impl;

import com.dimple.project.blog.comment.domain.Comment;
import com.dimple.project.blog.comment.mapper.CommentMapper;
import com.dimple.project.blog.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: CommentServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentList(Comment comment) {
        List<Comment> comments = commentMapper.selectCommentList(comment);
        for (Comment temp : comments) {
            Integer replyId = temp.getReplyId();
            if (replyId != null) {
                temp.setParentComment(commentMapper.selectCommentById(replyId));
            }
        }
        return comments;
    }

    @Override
    public int deleteCommentById(Integer id) {
        return commentMapper.deleteCommonById(id);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int increaseGoodCount(Integer pageId, Integer commentId) {
        return commentMapper.increaseGoodCount(pageId, commentId);
    }

    @Override
    public int increaseBadCount(Integer pageId, Integer commentId) {
        return commentMapper.increaseBadCount(pageId, commentId);
    }
}
