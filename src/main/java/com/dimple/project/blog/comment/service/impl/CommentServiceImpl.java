package com.dimple.project.blog.comment.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.common.utils.text.Convert;
import com.dimple.project.blog.comment.domain.Comment;
import com.dimple.project.blog.comment.mapper.CommentMapper;
import com.dimple.project.blog.comment.service.CommentService;
import com.dimple.project.common.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.List;
import java.util.Objects;

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
    @Autowired
    MailService mailService;
    @Autowired
    TemplateEngine templateEngine;

    @Override
    public List<Comment> selectCommentListForFront(Comment comment) {
        List<Comment> comments = commentMapper.selectCommentListForFront(comment);
        for (Comment temp : comments) {
            Integer replyId = temp.getReplyId();
            if (replyId != null) {
                temp.setParentComment(commentMapper.selectCommentById(replyId));
            }
        }
        return comments;
    }

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
    public int deleteBgCommentByIds(String ids) {
        return commentMapper.deleteBgCommentByIds(Convert.toStrArray(ids));
    }

    @Override
    public int insertComment(Comment comment) {
        Objects.requireNonNull(comment, "系统异常，参数为空");
        if (comment.getReplyId() != null) {
            Comment replyComment = commentMapper.selectCommentById(comment.getReplyId());
            String email = replyComment.getEmail();
            mailService.sendReplyEmail(email,replyComment.getId(), replyComment.getContent(), comment.getContent(), comment.getUrl());
        }
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

    @Override
    public Comment selectCommentById(Integer id) {
        return commentMapper.selectCommentById(id);
    }

    @Override
    public int changeDisplayById(Integer id, boolean display) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setDisplay(display);
        comment.setUpdateBy(ShiroUtils.getSysUser().getLoginName());
        return commentMapper.updateComment(comment);
    }

}
