package com.dimple.project.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: MetaVo
 * @description: 路由显示信息
 * @author: Dimple
 * @date: 10/22/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaVo implements Serializable {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;
}
