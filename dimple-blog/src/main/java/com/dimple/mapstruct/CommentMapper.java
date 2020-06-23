package com.dimple.mapstruct;

import com.dimple.base.BaseMapper;
import com.dimple.domain.Comment;
import com.dimple.service.Dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @className: CommentMapper
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper extends BaseMapper<CommentDTO, Comment> {
}
