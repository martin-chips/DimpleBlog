package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogArchives;
import com.dimple.blog.front.service.mapper.BlogArchivesMapper;
import com.dimple.blog.front.service.service.BlogArchivesService;
import com.dimple.blog.front.service.service.bo.BlogArchivesBO;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BlogArchivesServiceImpl
 *
 * @author Dimple
 * @date 2/28/2023 11:09 AM
 */
@Service
public class BlogArchivesServiceImpl implements BlogArchivesService {
    @Autowired
    private BlogArchivesMapper archivesMapper;

    @Override
    public List<BlogArchivesBO> getArchives(String countType) {
        List<BlogArchives> archives = archivesMapper.getArchives(countType);
        return BeanMapper.convertList(archives, BlogArchivesBO.class);
    }
}
