package com.dimple.project.book.mapper;

import com.dimple.project.book.entity.NoteTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: NoteTagMapper
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
public interface NoteTagMapper {
    /**
     * 获取Tag列表
     *
     * @param noteTag 带查询条件的Tag
     * @return tagList
     */
    List<NoteTag> selectNoteTagList(NoteTag noteTag);

    /**
     * 新增Tag
     *
     * @param noteTag tag实体
     * @return 受影响的行数
     */
    int insertNoteTag(NoteTag noteTag);

    /**
     * 根据Id查询tag
     *
     * @param id id
     * @return tag实体
     */
    NoteTag selectNoteTagById(Long id);

    /**
     * 更新Tag
     *
     * @param noteTag tag实体
     * @return 受影响的行数
     */
    int updateNoteTag(NoteTag noteTag);

    /**
     * 删除Tag
     *
     * @param ids tag的id
     * @return 受影响的行数
     */
    int deleteNoteTagByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 根据title查询tag
     *
     * @param tag tag title
     * @return tag
     */
    NoteTag selectNoteTagByTitle(String tag);
}
