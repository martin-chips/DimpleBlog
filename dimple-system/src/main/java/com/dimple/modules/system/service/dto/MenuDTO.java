package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @className: MenuDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class MenuDTO extends BaseDTO implements Serializable {

    private Long id;

    private List<MenuDTO> children;

    private Integer type;

    private String permission;

    private String title;

    private Integer menuSort;

    private String path;

    private String component;

    private Long pid;

    private Integer subCount;

    private Boolean iFrame;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    public Boolean getHasChildren() {
        return subCount > 0;
    }

    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuDTO menuDto = (MenuDTO) o;
        return Objects.equals(id, menuDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
