package com.dimple.service.Dto;

import com.dimple.base.BaseDTO;
import com.dimple.domain.Category;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @className: BlogDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@ToString(callSuper = true)
public class BlogDTO extends BaseDTO implements Serializable {
    private Long id;
    private String title;
    private String summary;
    private String headerImg;
    private Integer headerImgType;
    private String content;
    private String htmlContent;
    private Boolean status;
    private Boolean comment;
    private Boolean support;
    private Long weight;
    private Long like;
    private Long click;
    private Category category;
}
