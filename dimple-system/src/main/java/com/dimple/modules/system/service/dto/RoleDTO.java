package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @className: RoleDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class RoleDTO extends BaseDTO implements Serializable {

    private Long id;

    private Set<MenuDTO> menus;

    private String name;

    private Integer level;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDTO roleDto = (RoleDTO) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
