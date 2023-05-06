package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogArchiveService;
import com.dimple.blog.api.bo.BlogArchiveItemBO;
import com.dimple.blog.api.bo.BlogArchivesBO;
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
public class RemoteBlogArchiveServiceFallbackFactory implements FallbackFactory<RemoteBlogArchiveService> {
    @Override
    public RemoteBlogArchiveService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return new RemoteBlogArchiveService() {
            @Override
            public ResponseEntity<List<BlogArchiveItemBO>> getArchives(String source) {
                return null;
            }

            @Override
            public ResponseEntity<List<BlogArchivesBO>> getArchiveList(String countType, String source) {
                return null;
            }
        };
    }
}
