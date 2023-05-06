package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.BlogArchiveItemBO;
import com.dimple.blog.api.bo.BlogArchivesBO;

import java.util.List;

public interface BlogArchiveService {
    List<BlogArchivesBO> getArchiveList(String countType);

    List<BlogArchiveItemBO> getArchives();
}
