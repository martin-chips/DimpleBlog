package com.dimple.service.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.OperateLog;
import com.dimple.service.dto.LogErrorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: LogErrorMapper
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends BaseMapper<LogErrorDTO, OperateLog> {

}
