package com.dimple.service;

import com.dimple.bean.Blog;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FrontService
 * @Description:
 * @Auther: Owenb
 * @Date: 12/17/18 15:38
 * @Version: 1.0
 */
public interface FrontService {
    /**
     * 获取首页显示的分类的数据信息
     * Map的key为分类的名称
     * Map的value为分类下的具体的博客的信息
     *
     * @return
     */
    Map<String, List<Blog>> getCategoryInfo();

    /**
     * 获取首页展示的Category的名字
     *
     * @return
     */
    List<String> selectCategoryNameToDisplay();

    List<List<Blog>> getBlogsInfo();

    /**
     * 获取大家在看的博文
     *
     * @return
     */
    List<Blog> getBlogsPeopleSee();

    /**
     * 获取最新博文
     *
     * @return
     */
    List<Map<String, Object>> getNewestBlog();
}
