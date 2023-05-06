package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.api.RemoteBlogTagService;
import com.dimple.blog.api.bo.BlogTagBO;
import com.dimple.blog.api.bo.BlogTagDTO;
import com.dimple.blog.front.service.service.BlogRestTagService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.PageUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
public class BlogRestTagServiceImpl implements BlogRestTagService {
    @Autowired
    private RemoteBlogTagService remoteBlogTagService;

    @Override
    public List<BlogTagBO> selectBlogTagByIds(List<Long> ids) {
        ResponseEntity<List<BlogTagBO>> blogTagListResponse = remoteBlogTagService.selectBlogTagListByIds(ids, SecurityConstants.INNER);
        if (blogTagListResponse.isOk()) {
            return blogTagListResponse.getData();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<BlogTagBO> selectBlogTagList(BlogTagBO blogTagBO) {
        ResponseEntity<List<BlogTagBO>> blogTagListResponse = remoteBlogTagService.selectBlogTagList(BeanMapper.convert(blogTagBO, BlogTagDTO.class), SecurityConstants.INNER);
        return PageUtils.getInnerPage(blogTagListResponse);
    }

}
