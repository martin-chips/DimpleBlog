package com.dimple.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.Blog;
import com.dimple.service.Dto.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: BlogMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlogMapper extends BaseMapper<BlogDTO, Blog> {
}
