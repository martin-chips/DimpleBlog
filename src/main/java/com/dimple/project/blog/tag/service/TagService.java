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

    /**
     * 批量添加tags
     *
     * @param tags 需要添加的tag的数组
     * @return 新加入的blog的id
     */
    List<Integer> insertTags(String[] tags);

    /**
     * 根据tag的title数组获取tag的id集合
     *
     * @param tags 需要获取id集合的tag的String数组
     * @return 获取到的id集合
     */
    List<Integer> selectTagIdsByTagTitles(String[] tags);

    /**
     * 检验tag的title是否是唯一
     *
     * @param title 需要检验的tag的title
     * @return 是否唯一
     */
    String checkTagTitleUnique(String title);
}
