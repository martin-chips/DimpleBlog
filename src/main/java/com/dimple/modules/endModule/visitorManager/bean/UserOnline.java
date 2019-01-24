package com.dimple.modules.endModule.visitorManager.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : UserOnline
 * @description : 在线用户
 * @date : 01/09/19 20:50
 */
@Data
public class UserOnline implements Serializable {

    private static final long serialVersionUID = 463102439730265709L;

    //session id
    private String id;

    private String userId;

    private String username;

    //IP
    private String ip;

    //状态
    private String status;

    //开始访问时间
    private Date startTime;

    //最后访问时间
    private Date lastAccessTime;

    //超时时间
    private Long timeout;

}
