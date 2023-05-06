package com.dimple.blog.service.service.impl;

import com.dimple.blog.api.bo.BlogArchiveItemBO;
import com.dimple.blog.api.bo.BlogArchivesBO;
import com.dimple.blog.service.mapper.BlogArchiveMapper;
import com.dimple.blog.service.service.BlogArchiveService;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogArchiveServiceImpl implements BlogArchiveService {
    @Autowired
    private BlogArchiveMapper blogArchiveMapper;

    @Override
    public List<BlogArchivesBO> getArchiveList(String countType) {
        return BeanMapper.convertList(blogArchiveMapper.getArchiveList(countType), BlogArchivesBO.class);
    }

    @Override
    public List<BlogArchiveItemBO> getArchives() {
        return BeanMapper.convertList(blogArchiveMapper.getArchives(), BlogArchiveItemBO.class);
    }
}
