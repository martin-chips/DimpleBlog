package com.dimple.modules.system.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.modules.system.domain.Dict;
import com.dimple.modules.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * @className: DictMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends BaseMapper<DictDTO, Dict> {

}
