package com.dimple.service;

import com.dimple.bean.Links;
import com.dimple.utils.message.Result;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LinksService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:29
 * @Version: 1.0
 */
public interface LinksService {
    /**
     * 获取所有的Links
     *
     * @param title     友链的标题
     * @param startTime 查询开始的时间
     * @param endTime   查询结束的时间
     * @param display   是否显示
     * @return
     */
    List<Links> getAllLinksHandled(String title, Date startTime, Date endTime, boolean display);

    /**
     * 更改Links的状态
     *
     * @param linkId 需要更改状态的Id
     * @param status 当前links的状态
     * @return 结果
     */
    Result switchLinkStatus(Integer linkId, Boolean status);

    /**
     * 删除Link
     *
     * @param linkIds 需要删除的Links的id，传入Integer数组
     * @return
     */
    Result deleteLinks(Integer[] linkIds);

    /**
     * 根据Link的Id获取Link的信息,
     *
     * @param linkId
     * @return
     */
    Result getLinkInfo(Integer linkId);

    /**
     * 添加友链
     *
     * @param links
     * @return
     */
    Result addLink(Links links);

    /**
     * 申请友链
     *
     * @param links
     * @return
     */
    Result applyLink(Links links);

    /**
     * 更新友链的信息
     *
     * @param links
     * @return
     */
    Result updateLinkInfo(Links links);
}
