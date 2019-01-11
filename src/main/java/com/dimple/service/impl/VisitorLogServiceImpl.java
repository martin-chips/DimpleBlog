package com.dimple.service.impl;

import com.dimple.bean.VisitorLog;
import com.dimple.repository.VisitorLogRepository;
import com.dimple.service.VisitorLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: VisitorLogServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:08
 * @Version: 1.0
 */
@Service
@Transactional
public class VisitorLogServiceImpl implements VisitorLogService {

    @Autowired
    VisitorLogRepository visitorLogRepository;

    @Override
    public Boolean checkoutIp(String ip) {
        //如果这个IP地址是获取不到的，就先让它通过
        if (StringUtils.isBlank(ip)) {
            return true;
        }
        return true;
    }

    @Override
    public List<VisitorLog> getAllVisitorLog() {

        return null;
    }

    @Override
    public void deleteVisitorLogById(Integer[] ids) {

    }

    @Override
    public VisitorLog getVisitorLogDetail(Integer id) {

        return null;
    }

    @Override
    public void insertVisitorLog(VisitorLog visitorLog) {
        visitorLogRepository.save(visitorLog);
    }


}
