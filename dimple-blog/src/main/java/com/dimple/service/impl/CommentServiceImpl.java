package com.dimple.service.impl;

import com.dimple.domain.Comment;
import com.dimple.mapstruct.CommentMapper;
import com.dimple.repository.CommentRepository;
import com.dimple.service.CommentService;
import com.dimple.service.Dto.CommentCriteria;
import com.dimple.exception.BadRequestException;
import com.dimple.utils.PageUtil;
import com.dimple.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @className: CommentServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Map<String, Object> queryAll(CommentCriteria criteria, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(commentPage.map(commentMapper::toDto));
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new BadRequestException("comment 不存在,Id为" + id));
    }

    @Override
    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Set<Long> ids) {
        commentRepository.deleteAllByIdIn(ids);
    }
}
