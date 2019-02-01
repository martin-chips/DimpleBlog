package com.dimple.modules.BackStageModule.FrontSetting.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

/**
 * @className: KeyValueDomain
 * @description:
 * @auther: Dimple
 * @date: 01/14/19 21:13
 * @version: 1.0
 */
@Data
@EqualsAndHashCode
public class KeyValueDomain {
    @Transient
    private String name;
    @Transient
    private Integer value;
}
