package com.dimple.project.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @className: UserRole
 * @description: 用户和角色关联 sys_user_role
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
