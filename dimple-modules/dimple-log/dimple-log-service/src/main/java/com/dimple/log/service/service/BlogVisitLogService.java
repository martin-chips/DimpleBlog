package com.dimple.log.service.service;

import com.dimple.system.api.model.BlogVisitLogBO;

import java.util.List;

/**
 * BlogVisitLogService
 *
 * @author Dimple
 * @date 3/6/2023 8:56 PM
 */
public interface BlogVisitLogService {
    List<BlogVisitLogBO> selectVisitLogList(BlogVisitLogBO blogVisitLogBO);

    int deleteVisitLogByIds(List<Long> ids);

    void cleanVisitLog();

    int insertVisitLog(BlogVisitLogBO blogVisitLogBO);

}
