package com.dimple.modules.endModule.visitorManager.service;

import com.dimple.modules.endModule.visitorManager.bean.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VisitorService
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:07
 * @Version: 1.0
 */
public interface VisitorService {

    Boolean checkoutIp(String ip);

    Page<Visitor> getAllVisitorLog(Pageable pageable, String location, Date startTime, Date endTime);

    void deleteVisitorLogById(Integer ids[]);

    Visitor getVisitorLogById(Integer id);

    void insertVisitorLog(Visitor visitor);

    long getHistoryVisitorCount();

    long getLocalMonthCount();

    void cleanVisitorLog();

    Page<List<Map<String, String>>> getAllVisitorCount(Pageable pageable, String location);

    /**
     * 设置访客标识（设置是否为黑名单）
     *
     * @param ip
     * @param blacklist true标识设置为黑名单，false表示取消黑名单
     */
    void setVisitorSign(String ip, Boolean blacklist);

    /**
     * 获取此IP的访问次数
     *
     * @param ip
     * @return
     */
    Integer getVisitorCountByIp(String ip);
}
