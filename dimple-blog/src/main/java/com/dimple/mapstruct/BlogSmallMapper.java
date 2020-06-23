package com.dimple.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.Blog;
import com.dimple.service.Dto.BlogSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: BlogMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlogSmallMapper extends BaseMapper<BlogSmallDTO, Blog> {
}
