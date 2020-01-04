package com.dimple.project.book.service.impl;

import com.dimple.common.enums.TagType;
import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.blog.service.TagService;
import com.dimple.project.book.domain.Note;
import com.dimple.project.book.mapper.NoteMapper;
import com.dimple.project.book.service.NoteService;
import com.dimple.project.common.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: NoteServiceImpl
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteMapper noteMapper;
    @Autowired
    TagService tagService;

    @Override
    public Note selectNoteById(Long id) {
        Note note = noteMapper.selectNoteById(id);
        note.setTagTitleList(getTagTitleListByNoteId((id)));
        return note;
    }

    private List<String> getTagTitleListByNoteId(Long noteId) {
        List<Tag> tagList = tagService.selectTagListByTypeAndId(TagType.NOTE.getType(), noteId);
        return tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNote(Note note) {
        note.setCreateBy(SecurityUtils.getUsername());
        int count = noteMapper.insertNote(note);
        tagService.updateTagMapping(TagType.NOTE.getType(), note.getId(), note.getTagTitleList());
        return count;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNote(Note note) {
        note.setUpdateBy(SecurityUtils.getUsername());
        int count = noteMapper.updateNote(note);
        tagService.updateTagMapping(TagType.NOTE.getType(), note.getId(), note.getTagTitleList());
        return count;
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

    @Override
    public List<String> selectNoteTagList(String query) {
        Tag tag = new Tag();
        tag.setTitle(query);
        tag.setType(TagType.NOTE.getType());
        List<Tag> tagList = tagService.selectTagList(tag);
        return tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
    }
}
