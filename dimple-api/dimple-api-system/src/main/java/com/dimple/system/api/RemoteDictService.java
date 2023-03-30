package com.dimple.system.api;

import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.system.api.factory.RemoteDictFallbackFactory;
import com.dimple.system.api.model.SysDictDataBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * RemoteDictService
 *
 * @author Dimple
 * @date 3/21/2023
 */
@FeignClient(contextId = "remoteDictService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDictFallbackFactory.class)
public interface RemoteDictService {
    @GetMapping(value = "/system/dict/type/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<SysDictDataBO> selectDictDataByType(@PathVariable("type") String type, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
