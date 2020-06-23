package com.dimple.modules.system.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * @className: RoleMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDTO, Role> {

}
