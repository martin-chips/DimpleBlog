package com.dimple.modules.system.domain;

import com.dimple.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * @className: Dict
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Entity
@Getter
@Setter
@Table(name = "sys_dict")
public class Dict extends BaseEntity implements Serializable {

    @Id
    @Column(name = "dict_id")
    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dict", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    @NotBlank
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;
}
