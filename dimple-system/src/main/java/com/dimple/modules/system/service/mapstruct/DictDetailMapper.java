package com.dimple.modules.system.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.modules.system.domain.DictDetail;
import com.dimple.modules.system.service.dto.DictDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: DictDetailMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDTO, DictDetail> {

}
