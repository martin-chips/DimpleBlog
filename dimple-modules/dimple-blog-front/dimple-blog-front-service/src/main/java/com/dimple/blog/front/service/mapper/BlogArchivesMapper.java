package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogArchiveItem;
import com.dimple.blog.front.service.entity.BlogArchives;

import java.util.List;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArchivesMapper {
    List<BlogArchives> getArchiveList(String countType);

    List<BlogArchiveItem> getArchives();

}
