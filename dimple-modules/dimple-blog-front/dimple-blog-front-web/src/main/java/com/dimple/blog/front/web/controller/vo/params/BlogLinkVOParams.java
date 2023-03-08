package com.dimple.blog.front.web.controller.vo.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * BlogLinkVOParams
 *
 * @author Dimple
 * @date 3/8/2023 7:02 PM
 */
@Data
public class BlogLinkVOParams {
    @NotNull
    @Length(max = 255)
    private String title;
    @NotNull
    @Length(max = 255)
    private String headerImage;
    @NotNull
    @Length(max = 255)
    private String description;
    @URL
    private String url;
    @Email
    private String email;
}
