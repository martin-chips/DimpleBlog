package com.dimple.modules.system.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @className: UserQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(blurry = "email,username,nickName")
    private String blurry;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
