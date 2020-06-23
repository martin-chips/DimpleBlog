package com.dimple.rest;

import com.dimple.base.BaseEntity;
import com.dimple.domain.Tag;
import com.dimple.service.Dto.TagCriteria;
import com.dimple.service.TagService;
import com.dimple.exception.BadRequestException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @className: TagController
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "博客：标签管理")
@RequestMapping("/api/tag")
public class TagController {
    private static final String ENTITY_NAME = "tag";
    private final TagService tagService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> queryAll(TagCriteria criteria,
                                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(tagService.queryAll(criteria, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Tag tag) {
        if (tag.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        tagService.create(tag);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@Validated(BaseEntity.Update.class) @RequestBody Tag tag) {
        tagService.update(tag);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        Assert.notEmpty(ids, "需要删除的分类Id不能为空");
        tagService.delete(ids);
        return ResponseEntity.ok().build();
    }

}
