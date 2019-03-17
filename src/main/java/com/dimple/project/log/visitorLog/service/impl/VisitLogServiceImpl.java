package com.dimple.project.log.visitorLog.service.impl;

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
}
