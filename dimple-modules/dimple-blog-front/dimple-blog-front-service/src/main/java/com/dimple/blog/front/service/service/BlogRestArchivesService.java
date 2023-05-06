package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogArchivesBO;

import java.util.List;

/**
 * BlogRestArchivesService
 *
 * @author Dimple
 * @date 2/28/2023 11:09 AM
 */
public interface BlogRestArchivesService {
    List<BlogArchivesBO> getArchiveList(String countType);

    List<BlogArchivesBO> getArchives(String countType);
}
