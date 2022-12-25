package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * Blog reading count
 *
 * @author Dimple
 */
@Data
public class ArticleReading extends BaseEntity {
    private Integer id;

    private Integer blogId;

}
