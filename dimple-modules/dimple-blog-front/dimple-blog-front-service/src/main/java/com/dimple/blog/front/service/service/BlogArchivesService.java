package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogArchivesBO;

import java.util.List;

/**
 * BlogArchivesService
 *
 * @author Dimple
 * @date 2/28/2023 11:09 AM
 */
public interface BlogArchivesService {
    List<BlogArchivesBO> getArchives(String countType);
}
