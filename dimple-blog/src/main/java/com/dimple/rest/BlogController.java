package com.dimple.rest;

import com.dimple.base.BaseEntity;
import com.dimple.domain.Blog;
import com.dimple.exception.BadRequestException;
import com.dimple.service.BlogService;
import com.dimple.service.Dto.BlogCriteria;
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
 * @className: BlogController
 * @description: blog controller
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "博客：博客管理")
@RequestMapping("/api/blog")
public class BlogController {
    private static final String ENTITY_NAME = "blog";

    private final BlogService blogService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> queryAll(BlogCriteria criteria,
                                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(blogService.queryAll(criteria, pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity queryById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Blog blog) {
        if (blog.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        blogService.create(blog);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@Validated(BaseEntity.Update.class) @RequestBody Blog blog) {
        blogService.update(blog);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Set<Long> ids) {
        Assert.notEmpty(ids, "需要删除的博客Id不能为空");
        blogService.delete(ids);
        return ResponseEntity.ok().build();
    }


}
