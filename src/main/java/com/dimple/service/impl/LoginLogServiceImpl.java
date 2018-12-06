package com.dimple.service.impl;

import com.dimple.bean.LoginLog;
import com.dimple.bean.LoginLogExample;
import com.dimple.dao.LoginLogMapper;
import com.dimple.service.LoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LoginLogServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 18:16
 * @Version: 1.0
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;


    @Override
    public Integer insertLoginLog(LoginLog loginLog) {
        int i = loginLogMapper.insertSelective(loginLog);
        return i;
    }

    @Override
    public List<LoginLog> getAllLoginLog(String location, String loginName, Boolean status, Date startTime, Date endTime, String osType, String browserType) {
        LoginLogExample loginLogExample = new LoginLogExample();
        LoginLogExample.Criteria criteria = loginLogExample.createCriteria();
        if (StringUtils.isNotBlank(location)) {
            criteria.andLoginLocationLike(location);
        }
        if (StringUtils.isNotBlank(loginName)) {
            criteria.andLoginNameLike(loginName);
        }
        if (startTime != null && endTime != null) {
            criteria.andLoginTimeBetween(startTime, endTime);
        } else if (startTime == null && endTime != null) {
            criteria.andLoginTimeLessThanOrEqualTo(endTime);
        } else if (startTime != null && endTime == null) {
            criteria.andLoginTimeGreaterThanOrEqualTo(startTime);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(osType)) {
            criteria.andOsLike(osType);
        }
        if (StringUtils.isNotBlank(browserType)) {
            criteria.andBrowserLike(browserType);
        }
        List<LoginLog> loginLogs = loginLogMapper.selectByExample(loginLogExample);
        return loginLogs;
    }

    @Override
    public Integer cleanLoginLog() {
        int i = loginLogMapper.deleteByExample(null);
        return i;
    }

    @Override
    public Integer deleteLoginLog(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        Integer count = 0;
        for (Integer id : ids) {
            count += loginLogMapper.deleteByPrimaryKey(id);
        }
        return count;
    }

    @Override
    public Map<String, Integer> getDetails() {
        int total = loginLogMapper.countByExample(null);
        LoginLogExample loginLogExampleForSuccess = new LoginLogExample();
        loginLogExampleForSuccess.createCriteria().andStatusEqualTo(true);
        int success = loginLogMapper.countByExample(loginLogExampleForSuccess);
        LoginLogExample loginLogExampleForFailure = new LoginLogExample();
        loginLogExampleForFailure.createCriteria().andStatusEqualTo(false);
        int failure = loginLogMapper.countByExample(loginLogExampleForFailure);
        Map<String, Integer> map = new HashMap<>();
        map.put("total", total);
        map.put("success", success);
        map.put("failure", failure);
        return map;
    }
}
