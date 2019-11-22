package com.dimple.project.blog.service;

import com.dimple.project.blog.domain.Tag;

import java.util.List;

/**
 * @className: TagService
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
public interface TagService {
    /**
     * 获取Tag列表
     *
     * @param tag 带查询条件的Tag
     * @return tagList
     */
    List<Tag> selectTagList(Tag tag);

    /**
     * 新增Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int insertTag(Tag tag);

    /**
     * 根据Id查询tag
     *
     * @param id id
     * @return tag实体
     */
    Tag selectTagById(Long id);

    /**
     * 更新Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int updateTag(Tag tag);

    /**
     * 删除Tag
     *
     * @param ids tag的id
     * @return 受影响的行数
     */
    int deleteTagByIds(String ids);
}
