package com.dimple.project.link.mapper;

import com.dimple.project.link.domain.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: LinkMapper
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
public interface LinkMapper {
    /**
     * 查询符合条件的link
     *
     * @param link 带有查询信息的link的实体类
     * @return 符合条件的link
     */
    List<Link> selectLinkList(Link link);

    /**
     * 新增link
     *
     * @param link 需要增加的link的实体类
     * @return 受影响的行数
     */
    int insertLink(Link link);

    /**
     * 根据id查询link的实体
     *
     * @param linkId 需要查询的link的id
     * @return link实例
     */
    Link selectLinkById(Integer linkId);

    /**
     * 删除友链信息
     *
     * @param ids 需要删除的友链的id
     * @return 受影响的行数
     */
    int deleteLinkByIds(Integer[] ids);

    /**
     * 更新link
     *
     * @param link 需要更新的link的实体
     * @return 受影响的行数
     */
    int updateLink(Link link);

    /**
     * 切换友链的显示状态
     *
     * @param id      需要切换的友链id
     * @param display 需要切换的状态
     * @return 受影响的行数
     */
    int changeDisplay(@Param("id") Integer id, @Param("display") Integer display);

    /**
     * 通过友链申请
     *
     * @param ids 需要通过的友链的id
     * @return 受影响的行数
     */
    int processedLinkByIds(Integer[] ids);

    /**
     * 根据display获取count统计
     *
     * @param dispaly dispaly状态
     * @return num
     */
    int selectLinkCountByDisplay(int dispaly);

    /**
     * 获取友链的可用或者不可用的count
     *
     * @param available 可用的status
     * @return num
     */
    int selectLinkCountByAvailable(int available);

    /**
     * 获取处理未处理的状态的count
     *
     * @param processed 处理的状态
     * @return num
     */
    int selectLinkCountByProcessed(int processed);

    /**
     * 获取所有的链接个数
     *
     * @return num
     */
    int selectLinkCount();

    /**
     * 增加友链的点击数
     *
     * @param id
     */
    void incrementLinkClickById(Integer id);

    /**
     * 获取前台展示的link
     *
     * @return link的集合
     */
    List<Link> selectLinkListFront();

    /**
     * 获取前台link 页面显示的link
     *
     * @return link的集合
     */
    List<Link> selectLinkListFrontWithSummary();
}
