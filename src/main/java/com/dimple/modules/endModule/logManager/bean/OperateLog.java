package com.dimple.modules.endModule.logManager.bean;

import com.dimple.framework.converter.BooleanToIntegerConverter;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : OperateLog
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Table(name = "operate_log", schema = "dimple_blog")
@Data
public class OperateLog {
    @Id
    @Column(name = "operate_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operateId;
    private String title;
    private Integer operateType;
    private String method;
    private String operateUrl;
    private String operatorName;
    private String operateIp;
    private String operateLocation;
    private String operateParam;
    private Boolean operateStatus;
    private String errorMsg;
    private Date operateTime;

}