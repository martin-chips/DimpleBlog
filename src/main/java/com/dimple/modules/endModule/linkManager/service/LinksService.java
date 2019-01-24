package com.dimple.modules.endModule.linkManager.service;

import com.dimple.modules.frontModule.front.domain.LinkDomain;
import com.dimple.modules.endModule.linkManager.bean.Link;
import com.dimple.framework.enums.LinksSearchCode;
import com.dimple.framework.message.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Map;

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
    Page<Link> getAllLinksHandled(String title, Date startTime, Date endTime, Boolean display, Pageable pageable);

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
    Result addLink(Link links);

    /**
     * 申请友链
     *
     * @param links
     * @return
     */
    Result applyLink(Link links);

    /**
     * 更新友链的信息
     *
     * @param links
     * @return
     */
    Result updateLinkInfo(Link links);

    /**
     * 获取所有的没有处理的Links
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param title     友链的标题
     * @return
     */
    Page<Link> getAllLinksUnHandled(Date startTime, Date endTime, String title, Pageable pageable);

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
     * @param pageable
     * @return
     */
    Page<Link> getLinksCondition(String title, Date startTime, Date endTime, Boolean display, LinksSearchCode searchCode, Pageable pageable);

    /**
     * 获取所有的友链（包括未处理的和已经处理的了的）
     *
     * @param pageable
     * @return
     */
    Page<Link> getLinksAll(Pageable pageable);

    /**
     * 获取所有的死链
     *
     * @param pageable
     * @return
     */
    Page<Link> getLinksDie(Pageable pageable);

    /**
     * 获取所有的已经隐藏的友链
     *
     * @param pageable
     * @return
     */
    Page<Link> getLinksHide(Pageable pageable);

    /**
     * 获取所有的已经显示的友链
     *
     * @param pageable
     * @return
     */
    Page<Link> getLinksDisplay(Pageable pageable);

    /**
     * 获取友链的个数分类的详细信息
     *
     * @return
     */
    Map<String, Integer> countStatusDetails();


    LinkDomain getLinksHanded();
}
