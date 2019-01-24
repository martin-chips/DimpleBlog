package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.Tag;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : TagService
 * @description :
 * @date : 12/28/18 20:17
 */
public interface TagService {

    Tag insertTag(Tag tag);

    int deleteTagById(Integer[] ids);

    void updateTag(Tag tag);

    List<Tag> selectTag();

    Tag selectTagById(Integer id);
}
