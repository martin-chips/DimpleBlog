package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @className: Link
 * @description: 友链对象 sys_link
 * @author: Dimple
 * @date: 2019-10-29
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link extends BaseEntity implements Serializable {

    /**
     * id
     */
    @NotNull(groups = {Update.class})
    private Long id;

    /**
     * 友链名称
     */
    @Length(min = 3, max = 50, message = "名称长度为{min}~{max}")
    private String title;

    /**
     * 友链地址
     */
    @URL(message = "请输入正确的Url地址")
    private String url;

    /**
     * 友链描述
     */
    @Length(min = 3, max = 500, message = "友链描述长度为{min}~{max}")
    private String description;

    /**
     * 网站图片
     */
    @URL(message = "请输入正确的网站图片地址")
    private String headerImg;

    /**
     * 1表示审核通过,0表示未审核
     */
    private Boolean status;

    /**
     * 是否显示友链
     */
    private Boolean display;

    /**
     * 站长邮箱地址
     */
    @Email(message = "请输入正确的站长邮箱地址")
    private String email;

    /**
     * 权重
     */
    private Long weight;

}
