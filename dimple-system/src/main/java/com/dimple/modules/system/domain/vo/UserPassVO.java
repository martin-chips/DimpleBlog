package com.dimple.modules.system.domain.vo;

import lombok.Data;

/**
 * @className: UserPassVO
 * @description: 修改密码的 Vo 类
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class UserPassVO {

    private String oldPass;

    private String newPass;
}
