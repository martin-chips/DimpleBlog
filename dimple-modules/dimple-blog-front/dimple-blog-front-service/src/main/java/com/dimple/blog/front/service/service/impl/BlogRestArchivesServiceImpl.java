package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.api.RemoteBlogArchiveService;
import com.dimple.blog.api.bo.BlogArchiveItemBO;
import com.dimple.blog.api.bo.BlogArchivesBO;
import com.dimple.blog.front.service.service.BlogRestArchivesService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * BlogRestArchivesServiceImpl
 *
 * @author Dimple
 * @date 2/28/2023 11:09 AM
 */
@Service
@Slf4j
public class BlogRestArchivesServiceImpl implements BlogRestArchivesService {
    @Autowired
    private RemoteBlogArchiveService remoteBlogArchiveService;

    @Override
    public List<BlogArchivesBO> getArchiveList(String countType) {
        ResponseEntity<List<BlogArchivesBO>> blogArchiveListResponse = remoteBlogArchiveService.getArchiveList(countType, SecurityConstants.INNER);
        if (blogArchiveListResponse.isOk()) {
            return blogArchiveListResponse.getData();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<BlogArchivesBO> getArchives(String countType) {
        // group by year
        ResponseEntity<List<BlogArchiveItemBO>> blogArchiveItemResponse = remoteBlogArchiveService.getArchives(SecurityConstants.INNER);
        if (!blogArchiveItemResponse.isOk()) {
            return Lists.newArrayList();
        }
        List<BlogArchiveItemBO> archives = blogArchiveItemResponse.getData();
        Map<String, List<BlogArchiveItemBO>> createTimeAndItemMap = archives.stream()
                .collect(Collectors.groupingBy(e -> DateUtils.parseDateToStr("yyyy-MM", e.getCreateTime())));
        // get page total info.
        List<BlogArchivesBO> results = BeanMapper.convertList(archives, BlogArchivesBO.class);
        results.clear();
        List<BlogArchivesBO> blogArchivesBOS = new ArrayList<>();
        for (Map.Entry<String, List<BlogArchiveItemBO>> entry : createTimeAndItemMap.entrySet()) {
            BlogArchivesBO blogArchivesBO = new BlogArchivesBO();
            blogArchivesBO.setDateStr(entry.getKey());
            List<BlogArchiveItemBO> blogArchiveItemBOS = BeanMapper.convertList(entry.getValue(), BlogArchiveItemBO.class);
            blogArchivesBO.setItems(blogArchiveItemBOS);
            blogArchivesBOS.add(blogArchivesBO);
        }
        results.addAll(blogArchivesBOS.stream().sorted(Comparator.comparing(e -> e.getDateStr())).collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
            Collections.reverse(list);
            return list;
        })));

        return results;
    }
}
