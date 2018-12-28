package com.dimple.service.impl;

import com.dimple.repository.VisitorRepository;
import com.dimple.service.VisitorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: VisitorServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:08
 * @Version: 1.0
 */
@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Override
    public Boolean checkoutIp(String ip) {
        //如果这个IP地址是获取不到的，就先让它通过
        if (StringUtils.isBlank(ip)) {
            return true;
        }
        return true;
    }
}
