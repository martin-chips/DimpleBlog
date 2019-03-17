package com.dimple.project.blog.tag.service;

import com.dimple.project.blog.tag.domain.Tag;

import java.util.List;

/**
 * @className: TagService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface TagService {
    /**
     * 根据tag条件获取tag集合
     *
     * @param tag 带有条件的tag实体
     * @return 符合条件的tag实体
     */
    List<Tag> selectTagList(Tag tag);

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    int insertTag(Tag tag);

    /**
     * 根据id获取Tag实体类信息
     *
     * @param tagId tag的id
     * @return 具体Tag信息
     */
    Tag selectTagById(Integer tagId);

    /**
     * 更新tag
     *
     * @param tag 需要更新tag实体类
     * @return 受影响的行数
     */
    int updateTag(Tag tag);

    /**
     * 根据id批量删除tag
     *
     * @param ids 需要删除的tags的id
     * @return 受影响的行数
     */
    int deleteTagByIds(Integer[] ids);
}
