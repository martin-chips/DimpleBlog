package com.dimple.project.monitor.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @className: Blacklist
 * @description:
 * @author: Dimple
 * @date: 10/24/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Blacklist extends BaseEntity implements Serializable {
    /**
     * id
     */
    @NotNull(groups = {Update.class})
    private Long id;
    /**
     * ip 地址
     */
    @Pattern(regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$", message = "IP地址不合法")
    private String ip;
    /**
     * 封禁原因描述
     */
    @Length(min = 3, max = 50, message = "封禁原因长度为{min}~{max}")
    private String description;
    /**
     * 拦截次数
     */
    private Long interceptCount;
    /**
     * 上次拦截的Url
     */
    private String lastAccessUrl;
    /**
     * 上次访问的地址
     */
    private Date lastAccessTime;
}
