package com.dimple.project.blog.tag.service.impl;

import com.dimple.common.constant.TagConstants;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.blog.blog.mapper.BlogTagMapper;
import com.dimple.project.blog.tag.domain.Tag;
import com.dimple.project.blog.tag.mapper.TagMapper;
import com.dimple.project.blog.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public List<Tag> selectTagList(Tag tag) {
        List<Tag> tags = tagMapper.selectTagList(tag);
        for (Tag temp : tags) {
            int count = blogTagMapper.selectBlogTagCountByTagId(temp.getTagId());
            temp.setCount(count);
        }
        return tags;
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
        //删除之前需要在关联表中删除数据
        blogTagMapper.deleteBlogTagByTagIds(ids);
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

    @Override
    public String checkTagTitleUnique(String title) {
        Tag tag = tagMapper.selectTagByTagTitle(title);
        return tag == null ? TagConstants.TAG_TITLE_UNIQUE : TagConstants.TAG_TITLE_NOT_UNIQUE;
    }
}
