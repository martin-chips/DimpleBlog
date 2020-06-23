package com.dimple.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.Category;
import com.dimple.service.Dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: CategoryMapper
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends BaseMapper<CategoryDTO, Category> {
}
