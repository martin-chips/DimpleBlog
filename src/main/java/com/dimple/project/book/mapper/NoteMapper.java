package com.dimple.project.book.mapper;

import com.dimple.project.book.domain.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: NoteMapper
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
public interface NoteMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Note selectNoteById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param note 实例对象
     * @return 对象列表
     */
    List<Note> selectNoteList(Note note);

    /**
     * 新增数据
     *
     * @param note 实例对象
     * @return 影响行数
     */
    int insertNote(Note note);

    /**
     * 修改数据
     *
     * @param note 实例对象
     * @return 影响行数
     */
    int updateNote(Note note);

    /**
     * 通过主键删除数据
     *
     * @param ids
     * @return 影响行数
     */
    int deleteNoteByIds(@Param("ids") Long[] ids, @Param("username") String username);

}
