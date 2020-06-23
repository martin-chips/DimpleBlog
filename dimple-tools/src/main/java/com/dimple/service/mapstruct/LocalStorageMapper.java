package com.dimple.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.LocalStorage;
import com.dimple.service.dto.LocalStorageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: LocalStorageMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends BaseMapper<LocalStorageDTO, LocalStorage> {

}
