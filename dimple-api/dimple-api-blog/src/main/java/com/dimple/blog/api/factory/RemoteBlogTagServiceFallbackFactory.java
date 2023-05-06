package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogTagService;
import com.dimple.blog.api.bo.BlogArticleTagBO;
import com.dimple.blog.api.bo.BlogTagBO;
import com.dimple.blog.api.bo.BlogTagDTO;
import com.dimple.common.core.domain.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Blog服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteBlogTagServiceFallbackFactory implements FallbackFactory<RemoteBlogTagService> {
    @Override
    public RemoteBlogTagService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return new RemoteBlogTagService() {
            @Override
            public ResponseEntity<List<BlogTagBO>> selectBlogTagListByIds(List<Long> ids, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogTagBO>> selectBlogTagList(BlogTagDTO blogTagDTO, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogArticleTagBO>> selectBlogArticleTagByArticleId(Long articleId, String source) {
                return null;
            }
        };
    }
}
