package com.dimple.modules.system.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * @className: RoleSmallMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDTO, Role> {

}
