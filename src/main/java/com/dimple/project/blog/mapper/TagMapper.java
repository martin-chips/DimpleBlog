package com.dimple.project.blog.mapper;

import com.dimple.project.blog.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: TagMapper
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
public interface TagMapper {
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
    int deleteTagByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 根据blog id获取所有关联的Tag
     *
     * @param id blogId
     * @return tag list
     */
    List<Tag> selectTagListByBlogId(Long id);

    /**
     * 根据title查询tag
     *
     * @param tag tag title
     * @return tag
     */
    Tag selectTagByTitle(String tag);
}
