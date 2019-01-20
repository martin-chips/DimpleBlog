package com.dimple.modules.frontSetting.bean;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Setting
 * @description :
 * @date : 01/02/19 20:49
 */
@Entity
@Data
@Table(name = "setting", schema = "dimple_blog")
@RequiredArgsConstructor
public class Setting {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String settingKey;
    private String settingValue;
    private String note;

    public Setting(String settingKey, String settingValue) {
        this.settingKey = settingKey;
        this.settingValue = settingValue;
    }
}
