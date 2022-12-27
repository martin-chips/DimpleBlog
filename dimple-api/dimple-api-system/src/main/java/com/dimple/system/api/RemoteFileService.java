package com.dimple.system.api;

import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.system.api.factory.RemoteFileFallbackFactory;
import com.dimple.system.api.model.SysFileBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author Dimple
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<SysFileBO> upload(@RequestPart(value = "file") MultipartFile file);
}
