package com.dimple.service;

import com.dimple.bean.Links;
import com.dimple.bean.LinksDetails;
import com.dimple.constant.LinksSearchCode;
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
     * @param title      友链的标题
     * @param startTime  查询开始的时间
     * @param endTime    查询结束的时间
     * @param display    是否显示
     * @param searchCode
     * @return
     */
    List<Links> getAllLinksHandled(String title, Date startTime, Date endTime, Boolean display);

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
     * @param ids 需要删除的Links的id，传入Integer数组
     * @return
     */
    Result deleteLinks(Integer[] ids);

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

    /**
     * 获取友链列表的详情页面的信息
     *
     * @return
     */
    LinksDetails getDetails();

    /**
     * 获取所有的没有处理的Links
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param title     友链的标题
     * @return
     */
    List<Links> getAllLinksUnHandled(Date startTime, Date endTime, String title);

    /**
     * 通过友链的申请
     *
     * @param linkId 要通过的id
     * @return
     */
    Result passLinksApply(Integer linkId);

    /**
     * 获取没有处理的链接的个数
     *
     * @return
     */
    Integer getUnHandledLinksCount();

    /**
     * 根据具体的search code获取不同的返回结果
     *
     * @param title
     * @param startTime
     * @param endTime
     * @param display
     * @param searchCode
     * @return
     */
    List<Links> getLinksCondition(String title, Date startTime, Date endTime, Boolean display, LinksSearchCode searchCode);

    /**
     * 获取所有的友链（包括未处理的和已经处理的了的）
     *
     * @return
     */
    List<Links> getLinksAll();

    /**
     * 获取所有的死链
     *
     * @return
     */
    List<Links> getLinksDie();

    /**
     * 获取所有的已经隐藏的友链
     *
     * @return
     */
    List<Links> getLinksHide();

    /**
     * 获取所有的已经显示的友链
     *
     * @return
     */
    List<Links> getLinksDisplay();
}
