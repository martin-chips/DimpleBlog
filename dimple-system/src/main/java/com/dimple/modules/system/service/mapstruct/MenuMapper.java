package com.dimple.modules.system.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.modules.system.domain.Menu;
import com.dimple.modules.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * @className: MenuMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDTO, Menu> {
}
