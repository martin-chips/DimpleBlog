package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogArchiveItem;
import com.dimple.blog.front.service.entity.BlogArchives;
import com.dimple.blog.front.service.mapper.BlogArchivesMapper;
import com.dimple.blog.front.service.service.BlogArchivesService;
import com.dimple.blog.front.service.service.bo.BlogArchiveItemBO;
import com.dimple.blog.front.service.service.bo.BlogArchivesBO;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<BlogArchivesBO> getArchiveList(String countType) {
        List<BlogArchives> archives = archivesMapper.getArchiveList(countType);
        return BeanMapper.convertList(archives, BlogArchivesBO.class);
    }

    @Override
    public List<BlogArchivesBO> getArchives(String countType) {
        // group by year
        List<BlogArchiveItem> archives = archivesMapper.getArchives();
        Map<String, List<BlogArchiveItem>> createTimeAndItemMap = archives.stream().collect(Collectors.groupingBy(e -> DateUtils.parseDateToStr("yyyy-MM", e.getCreateTime())));
        // get page total info.
        List<BlogArchivesBO> results = BeanMapper.convertList(archives, BlogArchivesBO.class);
        results.clear();
        for (Map.Entry<String, List<BlogArchiveItem>> entry : createTimeAndItemMap.entrySet()) {
            BlogArchivesBO blogArchivesBO = new BlogArchivesBO();
            blogArchivesBO.setDateStr(entry.getKey());
            List<BlogArchiveItemBO> blogArchiveItemBOS = BeanMapper.convertList(entry.getValue(), BlogArchiveItemBO.class);
            blogArchivesBO.setItems(blogArchiveItemBOS);
            results.add(blogArchivesBO);
        }
        return results;
    }
}
