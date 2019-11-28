package com.dimple.project.book.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.book.mapper.NoteMapper;
import com.dimple.project.book.entity.Note;
import com.dimple.project.book.service.NoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: NoteServiceImpl
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteMapper noteMapper;

    @Override
    public Note selectNoteById(Long id) {
        return noteMapper.selectNoteById(id);
    }

    @Override
    public int insertNote(Note note) {
        return noteMapper.insertNote(note);
    }

    @Override
    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    @Override
    public List<Note> selectNoteList(Note note) {
        return noteMapper.selectNoteList(note);
    }

    @Override
    public int deleteNoteByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return noteMapper.deleteNoteByIds(ConvertUtils.toLongArray(ids), username);
    }
}