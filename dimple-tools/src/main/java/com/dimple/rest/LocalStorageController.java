package com.dimple.rest;

import com.dimple.annotation.OLog;
import com.dimple.domain.LocalStorage;
import com.dimple.service.LocalStorageService;
import com.dimple.service.dto.LocalStorageQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: LocalStorageController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "工具：本地存储管理")
@RequestMapping("/api/localStorage")
public class LocalStorageController {

    private final LocalStorageService localStorageService;

    @ApiOperation("查询文件")
    @GetMapping
    @PreAuthorize("@ps.check('storage:list')")
    public ResponseEntity<Object> query(LocalStorageQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(localStorageService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @OLog("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('storage:list')")
    public void download(HttpServletResponse response, LocalStorageQueryCriteria criteria) throws IOException {
        localStorageService.download(localStorageService.queryAll(criteria), response);
    }

    @ApiOperation("上传文件")
    @PostMapping
    @PreAuthorize("@ps.check('storage:add')")
    public ResponseEntity<Object> create(@RequestParam String name, @RequestParam("file") MultipartFile file) {
        localStorageService.create(name, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改文件")
    @PutMapping
    @PreAuthorize("@ps.check('storage:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody LocalStorage resources) {
        localStorageService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("多选删除")
    @DeleteMapping
    @ApiOperation("多选删除")
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        localStorageService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
