package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.blog.mapper.CommentMapper;
import com.dimple.project.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: CommentServiceImpl
 * @description:
 * @auther: Dimple
 * @date: 2019/10/26
 * @version: 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentList(Comment comment) {
        return commentMapper.selectCommentList(comment);
    }

    @Override
    public Comment selectCommentById(Long id) {
        return commentMapper.selectCommentById(id);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    public int deleteCommentById(Long id) {
        String username = SecurityUtils.getUsername();
        return commentMapper.deleteCommentById(id, username);
    }

    @Override
    public int incrementCommentGood(Long id) {
        return commentMapper.incrementCommentGood(id);
    }

    @Override
    public int incrementCommentBad(Long id) {
        return commentMapper.incrementCommentBad(id);
    }
}
