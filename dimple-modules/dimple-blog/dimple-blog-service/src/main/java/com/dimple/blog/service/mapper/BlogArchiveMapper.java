package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogArchiveItem;
import com.dimple.blog.service.entity.BlogArchives;

import java.util.List;

public interface BlogArchiveMapper {
    List<BlogArchives> getArchiveList(String countType);

    List<BlogArchiveItem> getArchives();
}
