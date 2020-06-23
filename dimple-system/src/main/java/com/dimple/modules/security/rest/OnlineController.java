package com.dimple.modules.security.rest;

import com.dimple.annotation.OLog;
import com.dimple.modules.security.service.OnlineUserService;
import com.dimple.utils.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * @className: OnlineController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
@Api(tags = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    @ApiOperation("查询在线用户")
    @GetMapping
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> query(String filter, Pageable pageable) {
        return new ResponseEntity<>(onlineUserService.getAll(filter, pageable), HttpStatus.OK);
    }

    @OLog("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check()")
    public void download(HttpServletResponse response, String filter) throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter), response);
    }

    @ApiOperation("踢出用户")
    @DeleteMapping
    @PreAuthorize("@ps.check()")
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            // 解密Key
            key = EncryptUtils.desDecrypt(key);
            onlineUserService.kickOut(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
