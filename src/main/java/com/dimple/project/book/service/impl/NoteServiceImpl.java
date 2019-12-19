package com.dimple.project.book.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.book.entity.Note;
import com.dimple.project.book.entity.NoteTag;
import com.dimple.project.book.mapper.NoteMapper;
import com.dimple.project.book.mapper.NoteTagMapper;
import com.dimple.project.book.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
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
    NoteTagMapper noteTagMapper;

    @Override
    public Note selectNoteById(Long id) {
        Note note = noteMapper.selectNoteById(id);
        note.setTagTitleList(getTagTitleListByNoteId((id)));
        return note;
    }

    private List<String> getTagTitleListByNoteId(Long noteId) {
        List<NoteTag> tagList = noteTagMapper.selectNoteTagByNoteId(noteId);
        List<String> tagTitleList = tagList.stream().map(NoteTag::getTitle).collect(Collectors.toList());
        return tagTitleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNote(Note note) {
        note.setCreateBy(SecurityUtils.getUsername());
        int count = noteMapper.insertNote(note);
        updateNoteTagMapping(note);
        return count;
    }

    private void updateNoteTagMapping(Note note) {
        //删除该noteId下的所有关联
        noteTagMapper.deleteNoteTagMappingByNoteId(note.getId());
        List<String> tagTitleList = note.getTagTitleList();
        if (ObjectUtils.isNotEmpty(tagTitleList)) {
            for (String title : tagTitleList) {
                //搜索所有的该tag
                NoteTag noteTag = noteTagMapper.selectNoteTagByTitle(title);
                if (noteTag != null) {
                    noteTagMapper.insertNoteTagMapping(note.getId(), noteTag.getId());
                } else {
                    NoteTag temp = new NoteTag(title, StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 1));
                    noteTagMapper.insertNoteTag(temp);
                    noteTagMapper.insertNoteTagMapping(note.getId(), temp.getId());
                }
            }
        }
    }

    /**
     * 获取随机数
     *
     * @param num 最大范围
     * @return 随机数
     */
    private int getRandomNum(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNote(Note note) {
        note.setUpdateBy(SecurityUtils.getUsername());
        int count = noteMapper.updateNote(note);
        updateNoteTagMapping(note);
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
        NoteTag tag = new NoteTag();
        tag.setTitle(query);
        List<NoteTag> tagList = noteTagMapper.selectNoteTagList(tag);
        return tagList.stream().map(NoteTag::getTitle).collect(Collectors.toList());
    }
}
