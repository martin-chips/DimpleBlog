package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @className: Category
 * @description: 分类对象 bg_category
 * @author: Dimple
 * @date: 2019-10-28
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseEntity {

    private Long id;

    /**
     * 分类名称
     */
    @Length(min = 3, max = 50, message = "分类名称长度为{min}~{max}个字符")
    private String title;

    /**
     * 描述
     */
    @Length(min = 10, max = 150, message = "分类名称长度为{min}~{max}个字符")
    private String description;

    /**
     * 是否推荐
     */
    @NotNull(message = "推荐设置不能为空")
    private Boolean support;

    private List<Blog> blogList;

}
