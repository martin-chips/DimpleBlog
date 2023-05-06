package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.api.RemoteBlogTagService;
import com.dimple.blog.api.bo.BlogArticleTagBO;
import com.dimple.blog.front.service.service.BlogRestArticleTagService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
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
public class BlogRestArticleTagServiceImpl implements BlogRestArticleTagService {
    @Autowired
    private RemoteBlogTagService remoteBlogTagService;

    @Override
    public List<BlogArticleTagBO> selectBlogArticleTagByArticleId(Long articleId) {
        ResponseEntity<List<BlogArticleTagBO>> articleTagResponse = remoteBlogTagService.selectBlogArticleTagByArticleId(articleId, SecurityConstants.INNER);
        if (articleTagResponse.isOk()) {
            return articleTagResponse.getData();
        }
        return null;
    }
}
