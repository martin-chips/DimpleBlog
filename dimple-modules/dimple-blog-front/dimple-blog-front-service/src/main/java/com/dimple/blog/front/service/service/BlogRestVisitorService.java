package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.GithubVisitorInfoBO;

/**
 * VisitorService
 *
 * @author Dimple
 * @date 2023/3/14 13:34
 */
public interface BlogRestVisitorService {
    GithubVisitorInfoBO getGithubVisitorInfo(String code);

}
