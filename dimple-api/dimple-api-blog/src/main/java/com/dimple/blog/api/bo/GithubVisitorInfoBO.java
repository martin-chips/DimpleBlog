package com.dimple.blog.api.bo;

import lombok.Data;

/**
 * GithubVisitorInfoBO
 *
 * @author Dimple
 * @date 2023/3/14 13:35
 */
@Data
public class GithubVisitorInfoBO {
    private String loginUsername;
    private String username;
    /**
     * linkUrl
     */
    private String link;

    private String email;

    private String avatars;

    private Long id;
}
