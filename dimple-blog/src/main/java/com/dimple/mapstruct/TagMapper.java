package com.dimple.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.Tag;
import com.dimple.service.Dto.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: TagMapper
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends BaseMapper<TagDTO, Tag> {
}
