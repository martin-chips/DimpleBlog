package com.dimple.service.Dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: CommentDTO
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Data
public class CommentCriteria {
    @Query(blurry = "content,description")
    private String blurry;
}