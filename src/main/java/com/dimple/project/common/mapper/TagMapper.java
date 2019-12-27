package com.dimple.project.common.mapper;

import com.dimple.project.common.domain.Tag;
import com.dimple.project.common.domain.TagMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: TagMapper
 * @description:
 * @author: Dimple
 * @date: 12/25/19
 */
public interface TagMapper {
    /**
     * 根据条件查询所有的Tag
     *
     * @param tag tag
     * @return Tag List
     */
    List<Tag> selectTagList(Tag tag);

    /**
     * 根据tag的id获取Tag实体
     *
     * @param id tag的id
     * @return Tag
     */
    Tag selectTagById(Long id);

    /**
     * 新增Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int insertTag(Tag tag);

    /**
     * 更新Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int updateTag(Tag tag);

    /**
     * 根据Id批量删除Tag
     *
     * @param ids      id
     * @param username 操作者账号
     * @return
     */
    int deleteTagByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 删除TagMapping关联
     *
     * @param tagMapping TagMapping关联
     * @return 受影响的行数
     */
    int deleteTagMapping(TagMapping tagMapping);

    /**
     * 根据Title查询Tag
     *
     * @param title title
     * @param type  类型
     * @return Tag tag
     */
    Tag selectTagByTitle(@Param("title") String title, @Param("type") Integer type);

    /**
     * 插入Tag关联
     *
     * @param tagMapping 关联
     * @return 受影响的行数
     */
    int insertTagMapping(TagMapping tagMapping);

    /**
     * 根据Tag的type和对应type的id获取TagList
     *
     * @param type 类型
     * @param id   对应类型的id
     * @return Tag list
     */
    List<Tag> selectTagListByType(@Param("type") Integer type, @Param("id") Long id);
}
