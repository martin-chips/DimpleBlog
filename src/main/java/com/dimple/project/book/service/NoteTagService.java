package com.dimple.project.book.service;

import com.dimple.project.book.entity.NoteTag;

import java.util.List;

/**
 * @className: TagService
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
public interface NoteTagService {
    /**
     * 获取Tag列表
     *
     * @param tag 带查询条件的Tag
     * @return tagList
     */
    List<NoteTag> selectNoteTagList(NoteTag tag);

    /**
     * 新增Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int insertNoteTag(NoteTag tag);

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
     * @param tag tag实体
     * @return 受影响的行数
     */
    int updateNoteTag(NoteTag tag);

    /**
     * 删除Tag
     *
     * @param ids tag的id
     * @return 受影响的行数
     */
    int deleteNoteTagByIds(String ids);
}
