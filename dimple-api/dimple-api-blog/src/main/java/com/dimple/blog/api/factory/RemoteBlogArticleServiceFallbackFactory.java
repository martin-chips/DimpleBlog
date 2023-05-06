package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogArticleService;
import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.api.model.BlogArticleDTO;
import com.dimple.common.core.domain.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Blog服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteBlogArticleServiceFallbackFactory implements FallbackFactory<RemoteBlogArticleService> {
    @Override
    public RemoteBlogArticleService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return new RemoteBlogArticleService() {
            @Override
            public ResponseEntity<List<BlogArticleBO>> selectBlogArticleList(BlogArticleDTO blogArticleDTO, String source) {
                return null;
            }

            @Override
            public ResponseEntity<BlogArticleBO> selectBlogArticleDetailById(Long id, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<KeyValue<Long, Long>>> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogArticleBO>> selectBlogArticlePrevNext(Long id, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogArticleBO>> selectBlogArticleByTagId(Long tagId, String source) {
                return null;
            }

            @Override
            public ResponseEntity<Integer> likeArticle(Long articleId, String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<KeyValue<Long, Long>>> getPvByArticleId(Collection<Long> ids, String source) {
                return null;
            }
        };
    }
}
