package com.dimple.log.service.mapper;

import com.dimple.log.service.entity.BlogVisitLog;

import java.util.List;

/**
 * BlogVisitLogMapper
 *
 * @author Dimple
 * @date 3/6/2023 8:33 PM
 */
public interface BlogVisitLogMapper {
    List<BlogVisitLog> selectVisitLogList(BlogVisitLog blogVisitLog);

    int deleteVisitLogByIds(List<Long> ids);

    void cleanVisitLog();

    int insertVisitLog(BlogVisitLog blogVisitLog);

}
