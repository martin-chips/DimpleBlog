package com.dimple.modules.system.rest;

import com.dimple.annotation.OLog;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.system.domain.Dict;
import com.dimple.modules.system.service.DictService;
import com.dimple.modules.system.service.dto.DictQueryCriteria;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * @className: DictController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：字典管理")
@RequestMapping("/api/dict")
public class DictController {

    private static final String ENTITY_NAME = "dict";
    private final DictService dictService;

    @OLog("导出字典数据")
    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('dict:list')")
    public void download(HttpServletResponse response, DictQueryCriteria criteria) throws IOException {
        dictService.download(dictService.queryAll(criteria), response);
    }

    @OLog("查询字典")
    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("@ps.check('dict:list')")
    public ResponseEntity<Object> queryAll() {
        return new ResponseEntity<>(dictService.queryAll(new DictQueryCriteria()), HttpStatus.OK);
    }

    @OLog("查询字典")
    @ApiOperation("查询字典")
    @GetMapping
    @PreAuthorize("@ps.check('dict:list')")
    public ResponseEntity<Object> query(DictQueryCriteria resources, Pageable pageable) {
        return new ResponseEntity<>(dictService.queryAll(resources, pageable), HttpStatus.OK);
    }

    @OLog("新增字典")
    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("@ps.check('dict:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Dict resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        dictService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改字典")
    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("@ps.check('dict:edit')")
    public ResponseEntity<Object> update(@Validated(Dict.Update.class) @RequestBody Dict resources) {
        dictService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除字典")
    @ApiOperation("删除字典")
    @DeleteMapping
    @PreAuthorize("@ps.check('dict:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        dictService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
