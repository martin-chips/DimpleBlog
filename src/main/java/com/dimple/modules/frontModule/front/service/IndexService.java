package com.dimple.modules.frontModule.front.service;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @className: IndexService
 * @description:
 * @auther: Owenb
 * @date: 01/23/19 17:19
 * @version: 1.0
 */
public interface IndexService {
    List<String> getCategorySupportName();

    List<List<Blog>> getBlogsInfo();

    Object getRotationByPlace(int i);

    /**
     * 获取最新博文
     *
     * @param pageable
     * @return
     */
    List<Map<String, Object>> getNewestBlog(Pageable pageable);
}
