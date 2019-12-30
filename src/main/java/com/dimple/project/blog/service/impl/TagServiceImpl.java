package com.dimple.project.blog.service.impl;

import com.dimple.common.enums.TagType;
import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.blog.service.TagService;
import com.dimple.project.common.domain.Tag;
import com.dimple.project.common.domain.TagMapping;
import com.dimple.project.common.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    @Override
    public int deleteTagMapping(TagMapping tagMapping) {
        return tagMapper.deleteTagMapping(tagMapping);
    }

    @Override
    public Tag selectTagByTitle(String title, Integer type) {
        return tagMapper.selectTagByTitle(title, type);
    }

    @Override
    public int insertTagMapping(TagMapping tagMapping) {
        return tagMapper.insertTagMapping(tagMapping);
    }

    @Override
    public void updateTagMapping(Integer type, Long id, List<String> tagTitleList) {
        //删除该Id下的所有关联
        TagMapping tagMapping = TagMapping.builder()
                .blogId(TagType.BLOG.getType().equals(type) ? id : null)
                .noteId(TagType.NOTE.getType().equals(type) ? id : null)
                .bookId(TagType.BOOK.getType().equals(type) ? id : null)
                .build();
        deleteTagMapping(tagMapping);

        if (ObjectUtils.isNotEmpty(tagTitleList)) {
            for (String title : tagTitleList) {
                //搜索所有的tag
                Tag tag = selectTagByTitle(title.trim(), TagType.BLOG.getType());
                if (tag != null) {
                    tagMapping.setTagId(tag.getId());
                    insertTagMapping(tagMapping);
                } else {
                    Tag temp = new Tag(title.trim(), StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 1), TagType.BLOG.getType());
                    insertTag(temp);
                    tagMapping.setTagId(temp.getId());
                    insertTagMapping(tagMapping);
                }
            }
        }
    }

    @Override
    public List<Tag> selectTagListByTypeAndId(Integer type, Long id) {
        return tagMapper.selectTagListByType(type, id);
    }

    /**
     * 获取随机数
     *
     * @param num 最大范围
     * @return 随机数
     */
    private int getRandomNum(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

}
