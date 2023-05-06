package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.BlogLinkBO;

import java.util.List;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogLinkService {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogLinkBO selectBlogLinkById(Long id);

    /**
     * 查询列表
     *
     * @param blogLink
     * @return 集合
     */
    List<BlogLinkBO> selectBlogLinkList(BlogLinkBO blogLink);

    /**
     * 新增
     *
     * @param blogLink
     * @return affected lines
     */
    int insertBlogLink(BlogLinkBO blogLink);

    /**
     * 修改
     *
     * @param blogLink
     * @return affected lines
     */
    int updateBlogLink(BlogLinkBO blogLink);

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return affected lines
     */
    int deleteBlogLinkByIds(Long[] ids);

    /**
     * 删除信息
     *
     * @param id 主键
     * @return affected lines
     */
    int deleteBlogLinkById(Long id);

    int addLinkVisitCount(Long id);
}
