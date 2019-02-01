package com.dimple.modules.BackStageModule.VisitorManager.service;

import com.dimple.modules.BackStageModule.VisitorManager.bean.Blacklist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @ClassName: BlackListService
 * @Description:
 * @Auther: Dimple
 * @Date: 2019/1/20 13:44
 * @Version: 1.0
 */
public interface BlackListService {
    /**
     * 添加或者移除黑名单
     *
     * @return
     */
    int insertBlackList(String ips[]);

    void deleteBlackList(Integer ids[]);

    Page<Blacklist> getAllBlacklist(Pageable pageable, Date startTime, Date endTime);
}
