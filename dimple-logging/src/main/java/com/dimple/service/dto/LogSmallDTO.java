package com.dimple.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @className: LogSmallDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class LogSmallDTO implements Serializable {

    private String description;

    private String requestIp;

    private Long time;

    private String address;

    private String browser;

    private Timestamp createTime;
}
