package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogCommentService;
import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.api.model.BlogCommentDTO;
import com.dimple.common.core.domain.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Blog服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteBlogCommentServiceFallbackFactory implements FallbackFactory<RemoteBlogCommentService> {
    @Override
    public RemoteBlogCommentService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return new RemoteBlogCommentService() {
            @Override
            public ResponseEntity<Integer> insertBlogComment(BlogCommentBO blogComment, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogCommentBO>> selectBlogCommentList(BlogCommentDTO blogCommentDTO, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogCommentBO>> selectBlogCommentByParentIds(List<Long> ids, String source) {
                return null;
            }

            @Override
            public ResponseEntity<Integer> addCommentLikeCount(Long id, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<KeyValue<Long, Long>>> selectBlogCommentCountByArticleId(Set<Long> articleIds, String source) {
                return null;
            }
        };
    }
}
