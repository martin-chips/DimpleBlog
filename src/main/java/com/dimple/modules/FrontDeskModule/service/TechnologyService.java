package com.dimple.modules.FrontDeskModule.service;

import com.dimple.modules.FrontDeskModule.domain.BlogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @className: TechnologyService
 * @description:
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */
public interface TechnologyService {


    Page<BlogDomain> getNewestBlog(Pageable pageable);
}
