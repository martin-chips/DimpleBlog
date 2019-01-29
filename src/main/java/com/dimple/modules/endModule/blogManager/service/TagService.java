package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

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

    Page<Tag> getTag(Pageable pageable, String title, Date startTime, Date endTime);

    Tag getTagById(Integer id);

    Tag getTagByTitle(String title);

    String[] getTagTitleByBlogId(Integer id);
}
