package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: Link
 * @description: 友链Mapper接口
 * @author: Dimple
 * @date: 2019-10-29
 */
public interface LinkMapper {
    /**
     * 查询友链
     *
     * @param id 友链ID
     * @return 友链
     */
    Link selectLinkById(Long id);

    /**
     * 查询友链列表
     *
     * @param link 友链
     * @return 友链集合
     */
    List<Link> selectLinkList(Link link);

    /**
     * 新增友链
     *
     * @param link 友链
     * @return 结果
     */
    int insertLink(Link link);

    /**
     * 修改友链
     *
     * @param link 友链
     * @return 结果
     */
    int updateLink(Link link);

    /**
     * 删除友链
     *
     * @param id 友链ID
     * @return 结果
     */
    int deleteLinkById(@Param("id") Long id, @Param("username") String username);

    /**
     * 批量删除友链
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteLinkByIds(@Param("ids") Long[] ids, @Param("username") String username);
}
