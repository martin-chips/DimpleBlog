package com.dimple.system.service.service.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeSelectBO
 *
 * @author Dimple
 */
@Data
public class TreeSelectBO {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectBO> children;

    public TreeSelectBO() {

    }

    public TreeSelectBO(SysMenuBO menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelectBO::new).collect(Collectors.toList());
    }
}
