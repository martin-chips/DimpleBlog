package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.blog.domain.Tag;
import com.dimple.project.blog.mapper.TagMapper;
import com.dimple.project.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: TagServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> selectTagList(Tag tag) {
        return tagMapper.selectTagList(tag);
    }

    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public Tag selectTagById(Long id) {
        return tagMapper.selectTagById(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagByIds(String ids) {
        return tagMapper.deleteTagByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }
}
