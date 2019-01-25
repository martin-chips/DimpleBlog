package com.dimple.modules.frontModule.front.service;

import org.springframework.data.domain.Pageable;

/**
 * @className: TechnologyService
 * @description:
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */
public interface TechnologyService {


    Object getNewestBlog(Pageable pageable);
}
