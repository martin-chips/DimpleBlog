package com.dimple.service;

import com.dimple.bean.VisitorLog;

import java.util.List;

/**
 * @ClassName: VisitorLogService
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:07
 * @Version: 1.0
 */
public interface VisitorLogService {

    Boolean checkoutIp(String ip);

    List<VisitorLog> getAllVisitorLog();

    void deleteVisitorLogById(Integer ids[]);

    VisitorLog getVisitorLogDetail(Integer id);

    void insertVisitorLog(VisitorLog visitorLog);

}
