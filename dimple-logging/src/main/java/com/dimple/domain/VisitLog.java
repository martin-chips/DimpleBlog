package com.dimple.domain;

import com.dimple.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @className: VisitLog
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Entity
@Table(name = "sys_visit_log")
public class VisitLog extends BaseEntity implements Serializable {

    @Id
    @Column(name = "visit_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestIp;

    private Long pageId;

    private String address;

    private String browser;

    private String os;

    private String entryUrl;

    private String url;

    private byte[] exceptionDetail;

    private Long time;

    private Boolean status;

    private String title;

    private String spider;

    @CreationTimestamp
    private Timestamp createTime;

}
