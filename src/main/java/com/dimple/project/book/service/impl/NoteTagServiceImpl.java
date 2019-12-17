package com.dimple.project.book.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.book.entity.NoteTag;
import com.dimple.project.book.mapper.NoteTagMapper;
import com.dimple.project.book.service.NoteTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: TagServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
@Service
public class NoteTagServiceImpl implements NoteTagService {

    @Autowired
    NoteTagMapper noteTagMapper;

    @Override
    public List<NoteTag> selectNoteTagList(NoteTag tag) {
        return noteTagMapper.selectNoteTagList(tag);
    }

    @Override
    public int insertNoteTag(NoteTag tag) {
        return noteTagMapper.insertNoteTag(tag);
    }

    @Override
    public NoteTag selectNoteTagById(Long id) {
        return noteTagMapper.selectNoteTagById(id);
    }

    @Override
    public int updateNoteTag(NoteTag tag) {
        return noteTagMapper.updateNoteTag(tag);
    }

    @Override
    public int deleteNoteTagByIds(String ids) {
        return noteTagMapper.deleteNoteTagByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }
}
