package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogLinkService;
import com.dimple.blog.api.bo.BlogLinkBO;
import com.dimple.blog.api.model.BlogLinkDTO;
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
public class RemoteBlogLinkServiceFallbackFactory implements FallbackFactory<RemoteBlogLinkService> {
    @Override
    public RemoteBlogLinkService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return new RemoteBlogLinkService() {
            @Override
            public ResponseEntity<Integer> insertBlogLink(BlogLinkBO blogLink, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogLinkBO>> selectBlogLinkList(BlogLinkDTO blogLinkDTO, String source) {
                return null;
            }

            @Override
            public ResponseEntity<Integer> addLinkVisitCount(Long id, String source) {
                return null;
            }
        };
    }
}
