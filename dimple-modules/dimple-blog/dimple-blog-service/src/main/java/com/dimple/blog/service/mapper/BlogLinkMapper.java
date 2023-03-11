package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogLink;

import java.util.List;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogLinkMapper {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogLink selectBlogLinkById(Long id);

    /**
     * 查询列表
     *
     * @param blogLink
     * @return 集合
     */
    List<BlogLink> selectBlogLinkList(BlogLink blogLink);

    /**
     * 新增
     *
     * @param blogLink
     * @return affected lines
     */
    int insertBlogLink(BlogLink blogLink);

    /**
     * 修改
     *
     * @param blogLink
     * @return affected lines
     */
    int updateBlogLink(BlogLink blogLink);

    /**
     * 删除
     *
     * @param id 主键
     * @return affected lines
     */
    int deleteBlogLinkById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @return affected lines
     */
    int deleteBlogLinkByIds(Long[] ids);
}
