package com.dimple.log.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.log.service.entity.BlogVisitLog;
import com.dimple.log.service.mapper.BlogVisitLogMapper;
import com.dimple.log.service.service.BlogVisitLogService;
import com.dimple.system.api.model.BlogVisitLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BlogVisitLogServiceImpl
 *
 * @author Dimple
 * @date 3/6/2023 8:56 PM
 */
@Service
public class BlogVisitLogServiceImpl implements BlogVisitLogService {
    @Autowired
    private BlogVisitLogMapper visitLogMapper;

    @Override
    public List<BlogVisitLogBO> selectVisitLogList(BlogVisitLogBO blogVisitLogBO) {
        BlogVisitLog blogVisitLog = BeanMapper.convert(blogVisitLogBO, BlogVisitLog.class);
        List<BlogVisitLog> blogVisitLogs = visitLogMapper.selectVisitLogList(blogVisitLog);
        return BeanMapper.convertList(blogVisitLogs, BlogVisitLogBO.class);
    }

    @Override
    public int deleteVisitLogByIds(List<Long> ids) {
        return visitLogMapper.deleteVisitLogByIds(ids);
    }

    @Override
    public void cleanVisitLog() {
        visitLogMapper.cleanVisitLog();
    }

    @Override
    public int insertVisitLog(BlogVisitLogBO blogVisitLogBO) {
        BlogVisitLog blogVisitLog = BeanMapper.convert(blogVisitLogBO, BlogVisitLog.class);
        return visitLogMapper.insertVisitLog(blogVisitLog);
    }
}
