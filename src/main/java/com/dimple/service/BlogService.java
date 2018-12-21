package com.dimple.service;

import com.dimple.bean.Blog;
import com.dimple.framework.enums.BlogStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BlogService
 * @Description:
 * @Auther: Owenb
 * @Date: 12/12/18 14:56
 * @Version: 1.0
 */
public interface BlogService {
    /**
     * 获得所有的Blog信息
     *
     * @param title
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    List<Blog> selectAllBlog(String title, Date startTime, Date endTime, Integer status);

    /**
     * 新增博客
     *
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 删除Blog
     *
     * @param blogId
     * @return
     */
    int deleteBlog(Integer blogId);

    /**
     * 更新博客的信息
     *
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 更换博客的状态
     *
     * @param id     博客的id
     * @param status 将要切换的状态（1表示发布，2表示草稿箱，3表示回收站）
     * @return 受影响的行数
     */
    int changeBlogStatus(Integer[] id, Integer status);

    /**
     * 根据Blog的Id获取Blog信息
     *
     * @param id Blog的ID
     * @return 该Id对应的博客
     */
    Blog selectBlogById(Integer id);

    /**
     * 获取博客状态的统计（有多少是已经发布了的，有多少是已经在草稿箱，有多少是在垃圾箱）
     *
     * @return Map
     */
    Map<String, Integer> selectCountOfBlogStatus();

    /**
     * 根据Blog的Id获取博客的详细信息（包括content）
     *
     * @param id
     * @return
     */
    Blog selectBlogByIdBlobs(Integer id);

    /**
     * 是否推荐博客
     *
     * @param ids    博客的ID
     * @param status 博客的当前的状态
     * @return
     */
    int supportBlog(Integer[] ids, Boolean status);

    /**
     * 获取博客的状态及数量详情页展示
     *
     * @param i
     * @return
     */
    int selectBlogCountByStatus(BlogStatus i);
}
