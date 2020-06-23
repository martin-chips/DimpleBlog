package com.dimple.domain;

import com.dimple.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @className: Category
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Entity
@Table(name = "bg_category")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Category extends BaseEntity implements Serializable {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    @NotNull(groups = {Update.class})
    private Long id;

    @ApiModelProperty("分类名称")
    @Length(min = 3, max = 50, message = "分类名称长度为{min}~{max}个字符")
    private String title;

    @ApiModelProperty("描述")
    @Length(min = 10, max = 150, message = "分类名称长度为{min}~{max}个字符")
    private String description;

    @ApiModelProperty("是否推荐,1表示推荐，2表示不推荐")
    @NotNull(message = "推荐设置不能为空")
    private Boolean support;
}
