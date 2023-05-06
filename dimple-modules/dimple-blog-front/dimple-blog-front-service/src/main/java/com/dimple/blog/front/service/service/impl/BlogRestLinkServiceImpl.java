package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.api.RemoteBlogLinkService;
import com.dimple.blog.api.bo.BlogLinkBO;
import com.dimple.blog.api.model.BlogLinkDTO;
import com.dimple.blog.front.service.service.BlogRestLinkService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.enums.BlogLinkStatus;
import com.dimple.common.core.utils.PageUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BlogRestLinkServiceImpl
 *
 * @author Dimple
 * @date 3/8/2023 7:55 PM
 */
@Service
public class BlogRestLinkServiceImpl implements BlogRestLinkService {
    @Autowired
    private RemoteBlogLinkService remoteBlogLinkService;

    @Override
    public int insertLink(BlogLinkBO blogLinkBO) {
        blogLinkBO.setLinkStatus(BlogLinkStatus.PENDING.getStatus());
        ResponseEntity<Integer> addLinkResponse = remoteBlogLinkService.insertBlogLink(blogLinkBO, SecurityConstants.INNER);
        if (addLinkResponse.isOk()) {
            return addLinkResponse.getData();
        }
        return -1;
    }

    @Override
    public List<BlogLinkBO> listBlogLink(BlogLinkBO blogLinkBO) {
        ResponseEntity<List<BlogLinkBO>> blogLinkListResponse = remoteBlogLinkService.selectBlogLinkList(BeanMapper.convert(blogLinkBO, BlogLinkDTO.class), SecurityConstants.INNER);
        return PageUtils.getInnerPage(blogLinkListResponse);
    }

    @Override
    public int addLinkVisitCount(Long id) {
        ResponseEntity<Integer> addLinkVisitCountResponse = remoteBlogLinkService.addLinkVisitCount(id, SecurityConstants.INNER);
        if (addLinkVisitCountResponse.isOk()) {
            return addLinkVisitCountResponse.getData();
        }
        return -1;
    }
}
