package ${package}.service.mapstruct;

import BaseMapper;
import ${package}.domain.${className};
import ${package}.service.dto.${className}Dto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @website https://el-admin.vip
* @author ${author}
* @date ${date}
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}Mapper extends BaseMapper
<${className}Dto, ${className}> {

}