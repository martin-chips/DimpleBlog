package com.dimple.project.book.service;

import com.dimple.project.book.entity.Note;

import java.util.List;


/**
 * @className: NoteService
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
public interface NoteService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Note selectNoteById(Long id);

    /**
     * 新增数据
     *
     * @param note 实例对象
     * @return 受影响的行数
     */
    int insertNote(Note note);

    /**
     * 修改数据
     *
     * @param note 实例对象
     * @return 受影响的行数
     */
    int updateNote(Note note);

    /**
     * 查询所有数据
     *
     * @param note 带有查询条件的note实体
     * @return note list
     */
    List<Note> selectNoteList(Note note);

    /**
     * 批量删除note
     *
     * @param ids id
     * @return 受影响的行数
     */
    int deleteNoteByIds(String ids);
}