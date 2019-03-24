package com.dimple.project.blog.tag.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.blog.tag.domain.Tag;
import com.dimple.project.blog.tag.mapper.TagMapper;
import com.dimple.project.blog.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TagServiceImpl
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
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
        tag.setCreateBy(ShiroUtils.getLoginName());
        return tagMapper.insertTag(tag);
    }

    @Override
    public Tag selectTagById(Integer tagId) {
        return tagMapper.selectTagById(tagId);
    }

    @Override
    public int updateTag(Tag tag) {
        tag.setUpdateBy(ShiroUtils.getLoginName());
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagByIds(Integer[] ids) {
        return tagMapper.deleteTagByIds(ids);
    }

    @Override
    public List<Integer> insertTags(String[] tagTitles) {
        List<Integer> tagIds = new ArrayList<>();
        for (String tagTitle : tagTitles) {
            if (tagMapper.selectTagByTagTitle(tagTitle) == null) {
                Tag tag = new Tag();
                tag.setTagTitle(tagTitle);
                tag.setCreateBy(ShiroUtils.getLoginName());
                tagMapper.insertTag(tag);
                tagIds.add(tag.getTagId());
            }
        }
        return tagIds;
    }

    @Override
    public List<Integer> selectTagIdsByTagTitles(String[] tags) {
        return tagMapper.selectTagIdsByTagTitles(tags);
    }
}
