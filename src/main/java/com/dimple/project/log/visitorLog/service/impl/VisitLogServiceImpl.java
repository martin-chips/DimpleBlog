package com.dimple.project.log.visitorLog.service.impl;

import com.dimple.project.dashboard.domain.BusinessCommonData;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import com.dimple.project.log.visitorLog.mapper.VisitLogMapper;
import com.dimple.project.log.visitorLog.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: VisitLogServiceImpl
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    VisitLogMapper visitLogMapper;

    @Override
    public List<VisitLog> selectVisitLogList(VisitLog visitLog) {
        return visitLogMapper.selectVisitLogList(visitLog);
    }

    @Override
    public int deleteVisitLogByIds(Integer[] ids) {
        return visitLogMapper.deleteVisitLogByIds(ids);
    }

    @Override
    public VisitLog selectVisitLogById(Integer visitId) {
        return visitLogMapper.selectVisitLogById(visitId);
    }

    @Override
    public void cleanVisitLog() {
        visitLogMapper.cleanVisitLog();
    }

    @Override
    public void insertVisitLog(VisitLog visitLog) {
        visitLogMapper.insertVisitLog(visitLog);
    }

    @Override
    public Integer selectVisitLogTotalCount() {
        return visitLogMapper.selectVisitLogTotalCount();
    }

    @Override
    public Integer selectVisitLogTodayCount() {
        return visitLogMapper.selectVisitLogTodayCount();
    }

    @Override
    public List<BusinessCommonData> selectSpiderData() {
        return visitLogMapper.selectSpiderData();
    }

    @Override
    public List<BusinessCommonData> selectVisitLogData(String startTime, String endTime) {
        if (startTime != null && "undefined".equals(startTime)) {
            startTime = null;
        }
        if (endTime != null && "undefined".equals(endTime)) {
            endTime = null;
        }
        return visitLogMapper.selectVisitLogData(startTime, endTime);
    }
}
