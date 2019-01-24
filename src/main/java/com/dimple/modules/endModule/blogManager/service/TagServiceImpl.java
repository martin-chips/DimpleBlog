package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.Tag;
import com.dimple.modules.endModule.blogManager.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : TagServiceImpl
 * @description :
 * @date : 12/28/18 20:17
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag insertTag(Tag tag) {
        if (tag == null || StringUtils.isBlank(tag.getTitle())) {
            return tag;
        }
        return tagRepository.save(tag);
    }

    @Override
    public int deleteTagById(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            tagRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public void updateTag(Tag tag) {
        if (tag == null) {
            return;
        }
        Tag one = tagRepository.findTagById(tag.getId());

    }

    @Override
    public List<Tag> selectTag() {
        return tagRepository.findAll();
    }

    @Override
    public Tag selectTagById(Integer id) {
        return tagRepository.findTagById(id);
    }
}
