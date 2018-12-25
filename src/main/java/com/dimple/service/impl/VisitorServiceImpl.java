package com.dimple.service.impl;

import com.dimple.bean.Visitor;
import com.dimple.bean.VisitorExample;
import com.dimple.dao.VisitorMapper;
import com.dimple.service.VisitorService;
import com.dimple.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: VisitorServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:08
 * @Version: 1.0
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public Boolean checkoutIp(String ip) {
        //如果这个IP地址是获取不到的，就先让它通过
        if (StringUtils.isBlank(ip)) {
            return true;
        }
        VisitorExample visitorExample = new VisitorExample();
        VisitorExample.Criteria criteria = visitorExample.createCriteria();
        criteria.andIpEqualTo(ip);
        int i = visitorMapper.countByExample(visitorExample);
        if (i > 0) {
            return false;
        }
        return true;
    }
}
