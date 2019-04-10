package com.dimple.project.link.service;

import com.dimple.project.link.domain.Link;

import java.util.List;
import java.util.Map;

/**
 * @className: LinkService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
public interface LinkService {
    /**
     * 根据条件获取符合条件的集合
     *
     * @param link 带有查询条件的link
     * @return 符合条件的集合
     */
    List<Link> selectLinkList(Link link);

    /**
     * 新增Link
     *
     * @param link 需要新增的link的实体类信息
     * @return 受影响的行数
     */
    int insertLink(Link link);

    /**
     * 根据Id查询友链
     *
     * @param linkId 需要查询的友链的id
     * @return 查询到的实体
     */
    Link selectLinkById(Integer linkId);

    /**
     * 根据id删除Link
     *
     * @param ids 需要删除的link
     * @return 受影响的行数
     */
    int deleteLinkByIds(String ids);

    /**
     * 更新link
     *
     * @param link 需要更新的link的实体
     * @return 受影响的行数
     */
    int updateLink(Link link);

    /**
     * 切换友链的状态显示
     *
     * @param ids     需要切换的友链的id
     * @param display 需要切换到的状态
     * @return
     */
    int changeDisplay(Integer ids, Integer display);

    /**
     * 通过友链申请
     *
     * @param ids 需要通过的友链的id
     * @return 受影响的行数
     */
    int processedLinkByIds(String ids);

    /**
     * 获取所有的count统计
     *
     * @return
     */
    Map<String, Integer> selectLinkCount();

    /**
     * 根据友链的id增加点击量
     *
     * @param id
     */
    void incrementLinkClickById(Integer id);

    /**
     * 获取前台暂时的link
     *
     * @return link的集合
     */
    List<Link> selectLinkListFront();

    /**
     * 获取前台link页面显示的link
     *
     * @return link集合
     */
    List<Link> selectLinkListFrontWithSummary();

    /**
     * 前台页面申请link
     *
     * @param link link实体
     * @return 受影响的行数
     */
    int applyLink(Link link);
}
