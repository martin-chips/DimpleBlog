package com.dimple.service;

import com.dimple.bean.Visitor;
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
}
