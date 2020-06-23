package com.dimple.service.impl;

import com.dimple.domain.Tag;
import com.dimple.mapstruct.TagMapper;
import com.dimple.repository.TagRepository;
import com.dimple.service.Dto.TagCriteria;
import com.dimple.service.TagService;
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
 * @className: TagServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Map<String, Object> queryAll(TagCriteria criteria, Pageable pageable) {
        Page<Tag> tagPage = tagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(tagPage.map(tagMapper::toDto));
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new BadRequestException("tag 不存在,Id 为" + id));
    }

    @Override
    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void update(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void delete(Set<Long> ids) {
        tagRepository.deleteAllByIdIn(ids);
    }
}
